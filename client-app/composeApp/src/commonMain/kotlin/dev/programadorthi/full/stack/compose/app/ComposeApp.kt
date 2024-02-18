package dev.programadorthi.full.stack.compose.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.programadorthi.full.stack.compose.app.user.LoginScreen

@Composable
internal fun ComposeApp() {
    MaterialTheme {
        LoginScreen()
    }
}