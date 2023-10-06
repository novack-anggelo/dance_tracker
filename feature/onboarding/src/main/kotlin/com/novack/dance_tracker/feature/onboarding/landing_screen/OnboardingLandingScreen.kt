package com.novack.dance_tracker.feature.onboarding.landing_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.novack.dance_tracker.core.ui.R

@Composable
internal fun OnboardingLandingRoute(
    onNextClick: () -> Unit
) {

}

@Composable
fun OnboardingLandingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.ONBOARDING_WELCOME_HEADER),
            style = MaterialTheme.typography.titleLarge
        )
        Column {
            Text(text = stringResource(id = R.string.ONBOARDING_FEATURES_HEADER), style = MaterialTheme.typography.titleSmall)
            Text(text = stringResource(id = R.string.ONBOARDING_FEATURES_TRAINING_TRACK), style = MaterialTheme.typography.bodyLarge)
            Text(text = stringResource(id = R.string.ONBOARDING_FEATURES_CLASSES), style = MaterialTheme.typography.bodyLarge)
            Text(text = stringResource(id = R.string.ONBOARDING_FEATURES_SOCIALS), style = MaterialTheme.typography.bodyLarge)
        }
        Text(text = stringResource(id = R.string.ONBOARDING_TODO), style = MaterialTheme.typography.titleSmall)
    }
}