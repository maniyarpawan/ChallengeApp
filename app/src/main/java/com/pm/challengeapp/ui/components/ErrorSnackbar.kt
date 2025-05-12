package com.pm.challengeapp.ui.components

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ErrorSnackbar(
    errorMessage: String?,
    snackbarHostState: SnackbarHostState,
    onDismissError: () -> Unit
) {
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            onDismissError()
        }
    }
    SnackbarHost(hostState = snackbarHostState)
}
