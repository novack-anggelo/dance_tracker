package com.novack.dance_tracker.feature.onboarding.landing_screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.novack.dance_tracker.feature.onboarding.landing_screen.OnboardingLandingRoute

const val onboardingLandingRoute = "onboarding_landing"

fun NavGraphBuilder.onboardingLandingScreen(onNextClick: () -> Unit) {
    composable(route = onboardingLandingRoute) {
        OnboardingLandingRoute(onNextClick = onNextClick)
    }
}