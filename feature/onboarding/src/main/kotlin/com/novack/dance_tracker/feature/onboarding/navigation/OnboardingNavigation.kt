package com.novack.dance_tracker.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

private const val ONBOARDING_GRAPH_ROUTE_PATTERN = "onboarding_graph"
const val onboardingRoute = "onboarding_route"

fun NavController.navigateToOnboardingGraph(
    navOptions: NavOptions? = null
) {
    this.navigate(ONBOARDING_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.onboardingGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = ONBOARDING_GRAPH_ROUTE_PATTERN,
        startDestination = onboardingRoute
    ) {
        composable(route = onboardingRoute) {
            // TODO
        }
        nestedGraphs()
    }
}