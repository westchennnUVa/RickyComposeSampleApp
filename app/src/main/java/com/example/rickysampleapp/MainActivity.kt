package com.example.rickysampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.rickysampleapp.ui.RickyApp
import com.example.rickysampleapp.ui.rememberRickyAppState
import com.example.rickysampleapp.ui.theme.RickySampleAppTheme
import com.rickysampleapp.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // todo this place is followed the place of Nia, need to consider why
            val appState = rememberRickyAppState()

            RickySampleAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // CharacterOverviewRoute(modifier = Modifier.padding(innerPadding))
                    // HomeScreen(modifier = Modifier.padding(innerPadding))
                    RickyApp(
                        appState = appState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
