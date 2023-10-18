package com.novack.dance_tracker.feature.onboarding.user_basic_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.dance_tracker.core.model.data.Gender
import com.novack.dance_tracker.feature.onboarding.user_basic_info.domain.use_case.ValidateNameLastNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import javax.inject.Inject

@HiltViewModel
class OnboardingUserBasicInfoViewModel @Inject constructor(
    private val validateNameLastNameUseCase: ValidateNameLastNameUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<OnboardingUserBasicInfoUiState> =
        MutableStateFlow(OnboardingUserBasicInfoUiState.Idle)

    private val _fieldsState: MutableStateFlow<FieldsState> = MutableStateFlow(FieldsState())
    val fieldsState = _fieldsState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = FieldsState()
    )

    private val _validationsState: MutableStateFlow<ValidationsState> = MutableStateFlow(
        ValidationsState()
    )

    val uiState = _uiState.asStateFlow().combine(_validationsState) { _, validationState ->
        if (validationState.isValid) {
            OnboardingUserBasicInfoUiState.Valid
        } else {
            OnboardingUserBasicInfoUiState.Invalid
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = OnboardingUserBasicInfoUiState.Idle
    )

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.OnNameChange -> onNameChange(uiEvent.name)
            is UiEvent.OnLastNameChange -> onLastNameChange(uiEvent.lastName)
            is UiEvent.OnDobChange -> onDoBChange(uiEvent.dob)
            is UiEvent.OnGenderChange -> onGenderChange(uiEvent.gender)
        }
    }

    // TODO this is probably over engineering, if not then this will probably be used in other classes
    private inline fun <T, reified P> updateFieldValidationStatus(
        result: List<T>,
        updateFieldOnInvalid: () -> Unit,
        updateFieldOnValid: () -> Unit
    ) {
        if (result.any { it is P }) {
            updateFieldOnInvalid()
        } else {
            updateFieldOnValid()
        }
    }

    private fun updateFormValidationStatus() = viewModelScope.launch {
        _validationsState.update {
            _validationsState.value.copy(isValid = formIsValid())
        }
    }

    private fun formIsValid(): Boolean {
        return _validationsState.value.nameErrors == null
                && _validationsState.value.lastNameErrors == null
                && _validationsState.value.dobErrors == null
                && _validationsState.value.genderErrors == null
    }

    private fun onNameChange(name: String) = viewModelScope.launch {
        if (containsOnlyAlphabeticalAndSpaces(name))
            _fieldsState.update {
                _fieldsState.value.copy(name = name)
            }

        val result = validateNameLastNameUseCase(name)
        updateFieldValidationStatus<ValidateNameLastNameUseCase.DtResult, ValidateNameLastNameUseCase.DtResult.Error>(
            result = result,
            updateFieldOnValid = {
                _validationsState.update {
                    _validationsState.value.copy(nameErrors = null)
                }
            },
            updateFieldOnInvalid = {
                _validationsState.update {
                    _validationsState.value.copy(nameErrors = result)
                }
            }
        )
        updateFormValidationStatus()
    }

    private fun onLastNameChange(lastName: String) = viewModelScope.launch {
        if (containsOnlyAlphabeticalAndSpaces(lastName))
            _fieldsState.update {
                _fieldsState.value.copy(lastName = lastName)
            }
        val result = validateNameLastNameUseCase(lastName)
        updateFieldValidationStatus<ValidateNameLastNameUseCase.DtResult, ValidateNameLastNameUseCase.DtResult.Error>(
            result = result,
            updateFieldOnValid = {
                _validationsState.update {
                    _validationsState.value.copy(lastNameErrors = null)
                }
            },
            updateFieldOnInvalid = {
                _validationsState.update {
                    _validationsState.value.copy(lastNameErrors = result)
                }
            }
        )
        updateFormValidationStatus()
    }

    private fun onDoBChange(dob: Long?) = viewModelScope.launch {
        val dobInstant = dob?.let { Instant.fromEpochMilliseconds(it) }
        dobInstant?.let {
            _fieldsState.update {
                _fieldsState.value.copy(dob = dobInstant)
            }
        }
    }

    private fun onGenderChange(gender: Gender) = viewModelScope.launch {
        _fieldsState.update {
            _fieldsState.value.copy(gender = gender)
        }
    }

    private fun containsOnlyAlphabeticalAndSpaces(input: String): Boolean {
        val regex = Regex("^(?=.*[a-zA-Z])[a-zA-Z ]+$")
        return input.isEmpty() || (regex.matches(input) && !input.contains("  "))
    }
}

sealed interface OnboardingUserBasicInfoUiState {
    data object Idle : OnboardingUserBasicInfoUiState

    data object Invalid : OnboardingUserBasicInfoUiState

    data object Valid : OnboardingUserBasicInfoUiState
}

data class ValidationsState(
    val nameErrors: List<ValidateNameLastNameUseCase.DtResult>? = null,
    val lastNameErrors: List<ValidateNameLastNameUseCase.DtResult>? = null,
    val dobErrors: List<String>? = null,
    val genderErrors: List<String>? = null,
    val isValid: Boolean = false
)

sealed interface UiEvent {
    data class OnNameChange(val name: String) : UiEvent
    data class OnLastNameChange(val lastName: String) : UiEvent
    data class OnDobChange(val dob: Long?) : UiEvent
    data class OnGenderChange(val gender: Gender) : UiEvent
}

data class FieldsState(
    val name: String? = null,
    val lastName: String? = null,
    val dob: Instant? = null,
    val gender: Gender = Gender.MALE
)