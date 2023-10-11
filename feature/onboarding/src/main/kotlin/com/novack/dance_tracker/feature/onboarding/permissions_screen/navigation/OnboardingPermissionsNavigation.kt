package com.novack.dance_tracker.feature.onboarding.permissions_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.novack.dance_tracker.feature.onboarding.permissions_screen.OnboardingPermissionsRoute

const val onboardingPermissionsRoute = "onboarding_permissions"

fun NavController.navigateToOnboardingPermissions(
    navOptions: NavOptions? = null
) {
    this.navigate(route = onboardingPermissionsRoute, navOptions)
}

fun NavGraphBuilder.onboardingPermissionsScreen(onNextClick: () -> Unit) {
    composable(route = onboardingPermissionsRoute) {
        OnboardingPermissionsRoute(onNextClick = onNextClick)
    }
}