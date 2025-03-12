package com.example.rickysampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.rickysampleapp.ui.RickyAppState
import com.rickysampleapp.character.navigation.characterDetailScreen
import com.rickysampleapp.character.navigation.navigateToCharacterDetail
import com.rickysampleapp.favorite.favoriteGraph
import com.rickysampleapp.home.navigation.HomeBaseRoute
import com.rickysampleapp.home.navigation.HomeRoute
import com.rickysampleapp.home.navigation.homeGraph
import com.rickysampleapp.home.navigation.homeScreen

@Composable
fun RickyNavHost(
    appState: RickyAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeBaseRoute,
        modifier = modifier
    ) {
        homeGraph(
            onCharacterClicked = { characterId -> navController.navigateToCharacterDetail(characterId) }
        )
        favoriteGraph()
        // TODO think if needed for a nested graph?
        characterDetailScreen()
    }
}