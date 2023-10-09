package com.novack.dance_tracker.core.design_system.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


/**
 * a compositionLocal for [DpSizes]
 */
val LocalDpSize = staticCompositionLocalOf { DpSizes }

object DpSizes {

    /**
     * Current size: 4.dp
     */
    val extraSmall = 4.dp

    /**
     * Current size: 8.dp
     */
    val small = 8.dp

    /**
     * Current size: 12.dp
     */
    val medium = 12.dp

    /**
     * Current size: 16.dp
     */
    val large = 16.dp

    /**
     * Current size: 24.dp
     */
    val extraLarge = 20.dp
}