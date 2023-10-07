package com.novack.dance_tracker.feature.onboarding.landing_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.novack.dance_tracker.core.design_system.theme.LocalDpSize
import com.novack.dance_tracker.core.ui.R

@Composable
internal fun OnboardingLandingRoute(
    onNextClick: () -> Unit
) {
    OnboardingLandingScreen(onNextClick = onNextClick)
}

// TODO improve screen arrangement
@Composable
fun OnboardingLandingScreen(onNextClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = LocalDpSize.current.large)) {
        Text(
            text = stringResource(id = R.string.ONBOARDING_WELCOME_HEADER),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = LocalDpSize.current.large, top = 42.dp)
        )
        Column {
            Text(
                text = stringResource(id = R.string.ONBOARDING_FEATURES_HEADER),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(id = R.string.ONBOARDING_FEATURES_TRAINING_TRACK),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = LocalDpSize.current.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.ONBOARDING_FEATURES_CLASSES),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.ONBOARDING_FEATURES_SOCIALS),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = LocalDpSize.current.extraSmall)
            )
        }
        Text(
            text = stringResource(id = R.string.ONBOARDING_TODO),
            style = MaterialTheme.typography.titleSmall
        )
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalDpSize.current.extraLarge)
        ) {
            Text(text = stringResource(id = R.string.ONBOARDING_LADING_CTA))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingLandingScreenPreview() {
    MaterialTheme {
        OnboardingLandingScreen {

        }
    }
}