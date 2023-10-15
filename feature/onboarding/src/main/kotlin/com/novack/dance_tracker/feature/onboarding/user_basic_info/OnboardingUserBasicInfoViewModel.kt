package com.novack.dance_tracker.feature.onboarding.user_basic_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.dance_tracker.core.model.data.Gender
import com.novack.dance_tracker.feature.onboarding.user_basic_info.domain.use_case.ValidateNameLastNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import javax.inject.Inject

@HiltViewModel
class OnboardingUserBasicInfoViewModel @Inject constructor(
    private val validateNameLastNameUseCase: ValidateNameLastNameUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<OnboardingUserBasicInfoUiState> =
        MutableStateFlow(OnboardingUserBasicInfoUiState.Idle())
    val uiState = _uiState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = OnboardingUserBasicInfoUiState.Idle()
    )

    private val _fieldsState: MutableStateFlow<FieldsState> = MutableStateFlow(FieldsState())
    val fieldsState = _fieldsState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = FieldsState()
    )

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.OnNameChange -> onNameChange(uiEvent.name)
            is UiEvent.OnLastNameChange -> validateNameLastNameUseCase(uiEvent.lastName)
            else -> {}
        }
    }

    private fun onNameChange(name: String) = viewModelScope.launch {
        if (containsOnlyAlphabeticalAndSpaces(name)) _fieldsState.emit(_fieldsState.value.copy(name = name))
        val result = validateNameLastNameUseCase(name)
    }

    private fun onLastNameChange(lastName: String) = viewModelScope.launch {
        if (containsOnlyAlphabeticalAndSpaces(lastName)) _fieldsState.emit(
            _fieldsState.value.copy(
                lastName = lastName
            )
        )
        val result = validateNameLastNameUseCase(lastName)
    }

    private fun containsOnlyAlphabeticalAndSpaces(input: String): Boolean {
        val regex = Regex("^(?=.*[a-zA-Z])[a-zA-Z ]+$")
        return input.isEmpty() || (regex.matches(input) && !input.contains("  "))
    }
}

sealed interface OnboardingUserBasicInfoUiState {
    data class Idle(
        val name: String? = null,
        val lastName: String? = null,
        val dob: Instant? = null,
        val gender: Gender? = null
    ) : OnboardingUserBasicInfoUiState

    data class Invalid(
        val nameError: String? = null,
        val lastNameError: String? = null,
        val dobError: String? = null,
        val genderError: String? = null
    ) : OnboardingUserBasicInfoUiState

    data object Valid : OnboardingUserBasicInfoUiState
}

sealed interface UiEvent {
    data class OnNameChange(val name: String) : UiEvent
    data class OnLastNameChange(val lastName: String) : UiEvent
    data class OnDobChange(val dob: Instant) : UiEvent
    data class OnGenderChange(val gender: Gender)
}

data class FieldsState(
    val name: String? = null,
    val lastName: String? = null,
    val dob: Instant? = null,
    val gender: Gender? = null
)