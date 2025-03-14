package com.example.rickysampleapp.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.toRoute
import com.example.rickysampleapp.navigation.RickyNavHost
import com.example.rickysampleapp.navigation.TopLevelDestination
import com.rickysampleapp.favorite.navigateToFavoriteGraph
import com.rickysampleapp.home.navigation.HomeBaseRoute
import com.rickysampleapp.home.navigation.navigateToHomeGraph

@Composable
fun RickyApp(
    appState: RickyAppState, modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val currentDestination = appState.currentDestination

    Scaffold(modifier = modifier, bottomBar = {
        NavigationBar {
            NavigationBarItem(selected = currentDestination?.hierarchy?.any {
                it.hasRoute(TopLevelDestination.HOME.baseRoute)
            } ?: false,
                onClick = { appState.navController.navigateToHomeGraph() },
                icon = {
                    Icon(
                        imageVector = TopLevelDestination.HOME.icon,
                        contentDescription = TopLevelDestination.HOME.contentDescription
                    )
                })
            NavigationBarItem(selected = currentDestination?.hierarchy?.any {
                it.hasRoute(TopLevelDestination.FAVORITE.baseRoute)
            } ?: false,
                onClick = { appState.navController.navigateToFavoriteGraph() },
                icon = {
                    Icon(
                        imageVector = TopLevelDestination.FAVORITE.icon,
                        contentDescription = TopLevelDestination.FAVORITE.contentDescription
                    )
                }
            )
        }
    }) { innerPadding ->
        RickyApp(appState, snackbarHostState, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun RickyApp(
    appState: RickyAppState, snackbarHostState: SnackbarHostState, modifier: Modifier = Modifier
) {
    RickyNavHost(
        appState = appState, modifier = modifier
    )
}