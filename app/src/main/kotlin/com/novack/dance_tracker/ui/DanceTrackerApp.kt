package com.novack.dance_tracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.novack.dance_tracker.navigation.DanceTrackerNavHost


@Composable
fun DanceTrackerApp(
    appState: DanceTrackerAppState = rememberDanceTrackerAppState()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DanceTrackerNavHost(appState = appState)
    }

}