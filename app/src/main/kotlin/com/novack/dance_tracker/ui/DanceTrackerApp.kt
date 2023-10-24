package com.novack.dance_tracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.novack.dance_tracker.navigation.DanceTrackerNavHost


@Composable
fun DanceTrackerApp(
    appState: DanceTrackerAppState = rememberDanceTrackerAppState()
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DanceTrackerNavHost(appState = appState)
        }
    }
}