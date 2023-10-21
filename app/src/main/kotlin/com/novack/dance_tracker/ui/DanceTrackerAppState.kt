package com.novack.dance_tracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDanceTrackerAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): DanceTrackerAppState {
    return remember(
        navController,
        coroutineScope,
    ) {
        DanceTrackerAppState(
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}


class DanceTrackerAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
}