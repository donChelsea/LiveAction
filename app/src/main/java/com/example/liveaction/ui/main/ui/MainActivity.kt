package com.example.liveaction.ui.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.liveaction.ui.main.LiveActionState
import com.example.liveaction.ui.main.LiveActionViewModel
import com.example.liveaction.ui.main.ScreenStatus
import com.example.liveaction.ui.screen_utils.GenericError
import com.example.liveaction.ui.screen_utils.GenericOffline
import com.example.liveaction.ui.screens.auth.ui.AuthScreen
import com.example.liveaction.ui.screens.home.ui.HomeScreen
import com.example.liveaction.ui.theme.LiveActionTheme
import com.example.liveaction.utils.AuthUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<LiveActionViewModel>()

        setContent {
            LiveActionTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LiveActionApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun LiveActionApp(viewModel: LiveActionViewModel) {
    val state by viewModel.state.collectAsState()

    LiveActionLayout(
        state = state
    )
}


@Composable
fun LiveActionLayout(state: LiveActionState) {
    when (state.screenStatus) {
        ScreenStatus.Initial,
        ScreenStatus.Loading, -> {
            if (state.authStatus == AuthUtils.AuthStatus.UNREGISTERED) {
                AuthScreen()
            } else {
                HomeScreen()
            }
        }
        ScreenStatus.Error -> GenericError()
        ScreenStatus.Offline -> GenericOffline()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiveActionTheme {
        LiveActionApp(hiltViewModel())
    }
}