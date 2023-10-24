package com.novack.dance_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.novack.dance_tracker.core.design_system.theme.Dance_trackerTheme
import dagger.hilt.android.AndroidEntryPoint
import com.novack.dance_tracker.ui.DanceTrackerApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dance_trackerTheme {
                DanceTrackerApp()
            }
        }
    }
}
