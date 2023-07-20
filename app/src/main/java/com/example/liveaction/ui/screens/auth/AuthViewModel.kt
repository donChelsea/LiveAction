package com.example.liveaction.ui.screens.auth

import com.example.liveaction.ui.screen_utils.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : AppViewModel<AuthAction, AuthEvent, AuthState>() {

    private val _state = MutableStateFlow(AuthState())
    override val state: StateFlow<AuthState>
        get() = _state.asStateFlow()

    override fun handleAction(action: AuthAction) {
        when (action) {
            AuthAction.FinishRegistration -> processRegistration()
            is AuthAction.UpdateName -> processName(action)
        }
    }

    private fun processName(action: AuthAction.UpdateName) {
        with(action) {
            if (name.isNotEmpty()) {
                _state.update { state -> state.copy(name = name) }
            }
        }
    }

    private fun processRegistration() {
        println(_state.value.name)
    }
}