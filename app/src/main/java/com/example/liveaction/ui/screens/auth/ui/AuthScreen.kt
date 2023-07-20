@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.liveaction.ui.screens.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liveaction.R
import com.example.liveaction.ui.screens.auth.AuthAction
import com.example.liveaction.ui.screens.auth.AuthEvent
import com.example.liveaction.ui.screens.auth.AuthState
import com.example.liveaction.ui.screens.auth.AuthViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun AuthScreen() {
    val viewModel = viewModel<AuthViewModel>()
    val state by viewModel.state.collectAsState()

    fun handleEvent(event: AuthEvent) = when (event) {
        else -> {}
    }

    LaunchedEffect(key1 = true) {
        viewModel.events.onEach(::handleEvent).launchIn(this)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AuthLayout(
            state = state,
            onAction = viewModel::handleAction
        )
    }
}

@Composable
fun AuthLayout(
    state: AuthState,
    onAction: (AuthAction) -> Unit,
) {
    RegistrationForm(
        state = state,
        onAction = onAction
    )
}

@Composable
fun RegistrationForm(
    state: AuthState,
    onAction: (AuthAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var job: Job? = null

    var nameInput by remember { mutableStateOf(state.name) }

    fun updateText(nameInput: String) {
        if (state.name != nameInput) {
            onAction(AuthAction.UpdateName(nameInput))
        }
    }

    LaunchedEffect(key1 = nameInput) {
        job?.cancel()

        updateText(nameInput)
        job = launch {
            delay(2000L)
            focusManager.clearFocus()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(24.dp),
            label = { Text(stringResource(id = R.string.first_name)) },
            value = nameInput,
            enabled = true,
            onValueChange = { nameInput = it },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onAction(AuthAction.FinishRegistration) },
        ) {
            Text(text = stringResource(id = R.string.register))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewRegistrationForm() {
    RegistrationForm(AuthState()) {}
}
