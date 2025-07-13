package com.dhc.designsystem.snackbar

import androidx.compose.material3.SnackbarHostState


suspend fun SnackbarHostState.showImmediately(
    message: String,
) {
    currentSnackbarData?.dismiss()
    showSnackbar(message)
}
