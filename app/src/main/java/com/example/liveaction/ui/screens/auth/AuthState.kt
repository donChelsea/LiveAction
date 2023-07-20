package com.example.liveaction.ui.screens.auth

import com.example.liveaction.utils.AuthUtils

data class AuthState(
    val name: String = "",
    val authStatus: AuthUtils.AuthStatus = AuthUtils.AuthStatus.UNREGISTERED
)

sealed class AuthAction {
    data class UpdateName(val name: String) : AuthAction()
    object FinishRegistration : AuthAction()
}

sealed class AuthEvent {}
