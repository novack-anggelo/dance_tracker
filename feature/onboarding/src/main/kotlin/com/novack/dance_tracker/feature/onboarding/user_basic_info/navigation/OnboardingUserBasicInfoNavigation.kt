package com.novack.dance_tracker.feature.onboarding.user_basic_info.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.novack.dance_tracker.feature.onboarding.user_basic_info.OnboardingUserBasicInfoRoute

const val onboarding_user_basic_info_route = "onboarding_user_basic_info"

fun NavController.navigateToOnboardingUserBasicInfoScreen(navOptions: NavOptions? = null) {
    this.navigate(onboarding_user_basic_info_route, navOptions)
}

fun NavGraphBuilder.onboardingUserBasicInfoScreen(onNextClick: () -> Unit) {
    composable(route = onboarding_user_basic_info_route) {
        OnboardingUserBasicInfoRoute(onNextClick = { /*TODO*/ })
    }
}