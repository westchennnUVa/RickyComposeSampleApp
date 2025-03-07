package com.example.rickysampleapp.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.rickysampleapp.navigation.RickyNavHost

@Composable
fun RickyApp(
    appState: RickyAppState,
    modifier: Modifier = Modifier
) {
   Surface(modifier = modifier) {
       val snackbarHostState = remember { SnackbarHostState() }

       RickyAppState(appState, snackbarHostState)
   }
}

@Composable
fun RickyAppState(
    appState: RickyAppState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    RickyNavHost(
        appState = appState,
        modifier = modifier
    )
}