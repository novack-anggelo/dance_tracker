package dance_tracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.novack.dance_tracker.feature.onboarding.landing_screen.navigation.onboardingLandingRoute
import com.novack.dance_tracker.feature.onboarding.landing_screen.navigation.onboardingLandingScreen
import com.novack.dance_tracker.feature.onboarding.navigation.onboardingGraph
import com.novack.dance_tracker.feature.onboarding.permissions_screen.navigation.navigateToOnboardingPermissions
import com.novack.dance_tracker.feature.onboarding.permissions_screen.navigation.onboardingPermissionsScreen
import dance_tracker.ui.DanceTrackerAppState

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
            nestedGraphs = {
                onboardingPermissionsScreen {  }
            }
        )
    }
}