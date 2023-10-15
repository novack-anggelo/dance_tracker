package com.novack.dance_tracker.feature.onboarding.user_basic_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun OnboardingUserBasicInfoRoute(
    onNextClick: () -> Unit,
    viewModel: OnboardingUserBasicInfoViewModel = hiltViewModel()
) {
    val fieldsState = viewModel.fieldsState.collectAsStateWithLifecycle().value

    OnboardingUserBasicInfoScreen(
        fieldsState = fieldsState,
        onUiEvent = viewModel::onEvent,
        onNextClick = onNextClick
    )
}

@Composable
fun OnboardingUserBasicInfoScreen(
    fieldsState: FieldsState,
    onUiEvent: (UiEvent) -> Unit,
    onNextClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = fieldsState.name.orEmpty(),
            onValueChange = { onUiEvent(UiEvent.OnNameChange(it)) }) // Name
        OutlinedTextField(
            value = fieldsState.lastName.orEmpty(),
            onValueChange = { onUiEvent(UiEvent.OnLastNameChange(it)) }) // last name
        // DOB
        // gender
        Button(onClick = {}) {

        }

    }
}