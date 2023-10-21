package com.novack.dance_tracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.novack.dance_tracker.feature.onboarding.landing_screen.navigation.onboardingLandingRoute
import com.novack.dance_tracker.feature.onboarding.landing_screen.navigation.onboardingLandingScreen
import com.novack.dance_tracker.feature.onboarding.navigation.onboardingGraph
import com.novack.dance_tracker.feature.onboarding.permissions_screen.navigation.navigateToOnboardingPermissions
import com.novack.dance_tracker.feature.onboarding.permissions_screen.navigation.onboardingPermissionsScreen
import com.novack.dance_tracker.feature.onboarding.user_basic_info.navigation.navigateToOnboardingUserBasicInfoScreen
import com.novack.dance_tracker.feature.onboarding.user_basic_info.navigation.onboardingUserBasicInfoScreen
import com.novack.dance_tracker.ui.DanceTrackerAppState

@Composable
fun DanceTrackerNavHost(
    appState: DanceTrackerAppState,
    modifier: Modifier = Modifier,
    startDestination: String = onboardingLandingRoute // TODO
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // TODO
        onboardingLandingScreen(onNextClick = navController::navigateToOnboardingPermissions)
        onboardingGraph(
            onLandingNextClick = navController::navigateToOnboardingPermissions,
            // TODO as onboarding is a separated feature I think this nested graph structure should be defined in that module, not here
            nestedGraphs = {
                onboardingPermissionsScreen(onNextClick = navController::navigateToOnboardingUserBasicInfoScreen)
                onboardingUserBasicInfoScreen {  }
            }
        )
    }
}