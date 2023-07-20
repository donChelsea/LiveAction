package com.example.liveaction.ui.main

import com.example.liveaction.utils.AuthUtils

data class LiveActionState(
    val screenStatus: ScreenStatus = ScreenStatus.Initial,
    val authStatus: AuthUtils.AuthStatus = AuthUtils.AuthStatus.UNREGISTERED
)

sealed class ScreenStatus {
    object Initial : ScreenStatus()
    object Loading : ScreenStatus()
    object Error : ScreenStatus()
    object Offline : ScreenStatus()
}

sealed class LiveActionAction {}

sealed class LiveActionEvent {}