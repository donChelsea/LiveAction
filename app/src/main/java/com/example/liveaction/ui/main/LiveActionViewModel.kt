package com.example.liveaction.ui.main

import com.example.liveaction.ui.screen_utils.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LiveActionViewModel @Inject constructor()
    : AppViewModel<LiveActionAction, LiveActionEvent, LiveActionState>() {

    private val _state = MutableStateFlow(LiveActionState())
    override val state: StateFlow<LiveActionState>
        get() = _state.asStateFlow()

    override fun handleAction(action: LiveActionAction) {
//        when (action) {
//
//        }
    }
}