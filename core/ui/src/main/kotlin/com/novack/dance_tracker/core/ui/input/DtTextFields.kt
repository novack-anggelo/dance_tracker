package com.novack.dance_tracker.core.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DtOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable() (() -> Unit)? = null,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errors: List<String>? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            singleLine = singleLine,
            isError = isError
        )
        errors?.forEach {
            Text(
                text = "• $it",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}