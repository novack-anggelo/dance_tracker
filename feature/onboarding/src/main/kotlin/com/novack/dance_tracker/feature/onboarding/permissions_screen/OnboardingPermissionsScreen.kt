package com.novack.dance_tracker.feature.onboarding.permissions_screen

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.novack.dance_tracker.core.design_system.theme.LocalDpSize
import com.novack.dance_tracker.core.ui.R

@Composable
internal fun OnboardingPermissionsRoute(onNextClick: () -> Unit) {
    OnboardingPermissionsScreen(onNextClick = onNextClick)
}

// TODO improve UI
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OnboardingPermissionsScreen(onNextClick: () -> Unit) {

    val permissionsState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
    } else {
        null
    }

    LaunchedEffect(key1 = Unit) {
        if(permissionsState == null) onNextClick()
        permissionsState?.launchPermissionRequest()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (permissionsState?.status) {
            is PermissionStatus.Denied -> {
               if(permissionsState.status.shouldShowRationale) {
                   PermissionsRationalView(onRetryPermissions = { permissionsState.launchPermissionRequest() }, onSkip = onNextClick)
               } else {
                   onNextClick()
               }
            }

            is PermissionStatus.Granted -> onNextClick()

            else -> Unit
        }
    }
}

@Composable
private fun PermissionsRationalView(onRetryPermissions: () -> Unit, onSkip: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = LocalDpSize.current.large)) {
        // TODO if we manage more permissions would be nice to have a cards carousel
        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
        ) {
            Column {
                Text(text = stringResource(id = R.string.ONBOARDING_PERMISSIONS_POST_NOTIFICATIONS_TITLE))
                Text(text = stringResource(id = R.string.ONBOARDING_PERMISSIONS_POST_NOTIFICATIONS_TYPE))
                Text(text = stringResource(id = R.string.ONBOARDING_PERMISSIONS_POST_NOTIFICATIONS_BODY))
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onSkip) {
                Text(text = stringResource(id = R.string.ONBOARDING_PERMISSIONS_SKIP_CTA))
            }
            Button(onClick = onRetryPermissions) {
                Text(text = stringResource(id = R.string.ONBOARDING_PERMISSIONS_REVIEW_PERMISSIONS_CTA))
            }
        }
    }
}
