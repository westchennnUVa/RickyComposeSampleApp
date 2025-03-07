package com.rickysampleapp.character.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rickysampleapp.character.CharacterDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailRoute(
    val characterId: Int
)

// TODO NavOptions, think about top level destination and nested destinations
fun NavController.navigateToCharacterDetail(characterId: Int, navOptions: NavOptions? = null) =
    navigate(CharacterDetailRoute(characterId), navOptions)

fun NavGraphBuilder.characterDetailScreen() {
    composable<CharacterDetailRoute> { navBackStackEntry ->
        val characterDetailRoute: CharacterDetailRoute = navBackStackEntry.toRoute()
        CharacterDetailScreen(characterId = characterDetailRoute.characterId)
    }
}