package dev.programadorthi.full.stack.compose.app.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.programadorthi.state.compose.rememberSaveableValidatorState
import dev.programadorthi.state.compose.rememberSaveableValueManagerAsState

@Composable
internal fun LoginScreen() {
    val viewModel = remember { LoginViewModel() }
    val (username, setUsername) = rememberSaveableValueManagerAsState { viewModel.username }
    val (password, setPassword) = rememberSaveableValueManagerAsState { viewModel.password }
    val (usernameValid, usernameMessages) = rememberSaveableValidatorState { viewModel.username }
    val (passwordValid, passwordMessages) = rememberSaveableValidatorState { viewModel.password }

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Login")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = username,
            onValueChange = setUsername,
            label = {
                Text("Username:")
            },
        )
        if (usernameValid.not()) {
            Text(usernameMessages.first(), color = Color.Red)
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password,
            onValueChange = setPassword,
            label = {
                Text("Password:")
            },
        )
        if (passwordValid.not()) {
            Text(passwordMessages.first(), color = Color.Red)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = viewModel::login) {
            Text("Sign in")
        }
    }
}