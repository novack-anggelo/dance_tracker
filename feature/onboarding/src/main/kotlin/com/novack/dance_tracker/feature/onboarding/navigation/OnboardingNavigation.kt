package com.novack.dance_tracker.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

private const val ONBOARDING_GRAPH_ROUTE_PATTERN = "onboarding_graph"
const val onboardingRoute = "onboarding_route"

fun NavController.onboardingGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {

}