package com.novack.dance_tracker.feature.onboarding.user_basic_info

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.novack.dance_tracker.core.design_system.icon.DtIcons
import com.novack.dance_tracker.core.model.data.Gender
import com.novack.dance_tracker.core.ui.R
import com.novack.dance_tracker.core.ui.input.DtOutlinedTextField
import com.novack.dance_tracker.feature.onboarding.user_basic_info.domain.use_case.ValidateNameLastNameUseCase

@Composable
internal fun OnboardingUserBasicInfoRoute(
    onNextClick: () -> Unit,
    viewModel: OnboardingUserBasicInfoViewModel = hiltViewModel()
) {
    val fieldsState = viewModel.fieldsState.collectAsStateWithLifecycle().value
    val validationsState = viewModel.validationsState.collectAsStateWithLifecycle().value
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    OnboardingUserBasicInfoScreen(
        fieldsState = fieldsState,
        validationsState = validationsState,
        uiState = uiState,
        onUiEvent = viewModel::onEvent,
        onNextClick = onNextClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingUserBasicInfoScreen(
    fieldsState: FieldsState,
    validationsState: ValidationsState,
    uiState: OnboardingUserBasicInfoUiState,
    onUiEvent: (UiEvent) -> Unit,
    onNextClick: () -> Unit
) {
    val context = LocalContext.current
    var showCalendarDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    var genderDropdownExpanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = fieldsState.dob) {
        fieldsState.dob?.let {
            datePickerState.setSelection(it.toEpochMilliseconds())
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        DtOutlinedTextField(
            value = fieldsState.name.orEmpty(),
            onValueChange = { onUiEvent(UiEvent.OnNameChange(it.trimStart())) },
            label = { Text(text = stringResource(id = R.string.NAME)) },
            singleLine = true,
            isError = validationsState.nameErrors?.isNotEmpty() ?: false,
            errors = validationErrorsToUiResource(validationsState.nameErrors, context)
        )
        DtOutlinedTextField(
            value = fieldsState.lastName.orEmpty(),
            onValueChange = { onUiEvent(UiEvent.OnLastNameChange(it.trimStart())) },
            label = { Text(text = stringResource(id = R.string.LAST_NAME)) },
            singleLine = true,
            isError = validationsState.lastNameErrors?.isNotEmpty() ?: false,
            errors = validationErrorsToUiResource(validationsState.lastNameErrors, context)
        )
        OutlinedTextField(
            value = fieldsState.dob?.toString()?.substring(0, 10).orEmpty(),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = DtIcons.calendar,
                    contentDescription = stringResource(id = R.string.OPEN_CALENDAR_CTA_DESCRIPTION),
                    modifier = Modifier.clickable { showCalendarDialog = true }
                )
            },
            label = { Text(text = stringResource(id = R.string.DOB)) }
        )
        if (showCalendarDialog) {
            // TODO add date validations
            DatePickerDialog(
                onDismissRequest = { showCalendarDialog = false },
                confirmButton = {
                    Button(onClick = {
                        showCalendarDialog = false
                        onUiEvent(UiEvent.OnDobChange(datePickerState.selectedDateMillis))
                    }) {
                        Text(text = stringResource(id = R.string.ACCEPT))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
        Surface(
            color = MaterialTheme.colorScheme.background,
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = getGenderResource(fieldsState.gender)))
                    IconButton(onClick = { genderDropdownExpanded = true }) {
                        Icon(
                            imageVector = DtIcons.chevronDown,
                            contentDescription = stringResource(id = R.string.ONBOARDING_EXPAND_GENDER_SELECTION_CTA_DESCRIPTION)
                        )
                    }
                }
                DropdownMenu(
                    expanded = genderDropdownExpanded,
                    onDismissRequest = { genderDropdownExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.GENDER_MASCULINE)) },
                        onClick = {
                            genderDropdownExpanded = false
                            onUiEvent(UiEvent.OnGenderChange(Gender.MALE))
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.GENDER_FEMININE)) },
                        onClick = {
                            genderDropdownExpanded = false
                            onUiEvent(UiEvent.OnGenderChange(Gender.FEMALE))
                        }
                    )
                }
            }
        }
        Button(onClick = {}, enabled = validationsState.isValid) {
            Text(text = stringResource(id = R.string.NEXT))
        }

    }
}

private fun getGenderResource(gender: Gender): Int {
    return when (gender) {
        is Gender.FEMALE -> R.string.GENDER_FEMININE
        is Gender.MALE -> R.string.GENDER_MASCULINE
    }
}

// TODO: There's probably a better way to do this, refactor if necessary
private fun validationErrorsToUiResource(
    validations: List<ValidateNameLastNameUseCase.DtResult>?,
    context: Context
): List<String>? {
    return validations?.let {
        it.mapNotNull { result ->
            when (result) {
                is ValidateNameLastNameUseCase.DtResult.Error.ErrorLength -> context.getString(
                    R.string.ONBOARDING_USER_INFO_LENGTH_ERROR,
                    2,
                    14
                )

                else -> null
            }
        }
    }
}