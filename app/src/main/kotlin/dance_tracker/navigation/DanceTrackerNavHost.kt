package dance_tracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.novack.dance_tracker.feature.onboarding.navigation.onboardingGraph
import dance_tracker.ui.DanceTrackerAppState

@Composable
fun DanceTrackerNavHost(
    appState: DanceTrackerAppState,
    modifier: Modifier = Modifier,
    startDestination: String = "overview" // TODO
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        onboardingGraph(
            nestedGraphs = {

            }
        )
    }
}