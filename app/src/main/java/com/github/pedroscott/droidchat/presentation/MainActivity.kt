package com.github.pedroscott.droidchat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.pedroscott.droidchat.presentation.navigation.ChatNavHost
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidChatTheme {
                ChatApp()
            }
        }
    }
}

@Composable
fun ChatApp() {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            ChatNavHost()
        }
    }
}