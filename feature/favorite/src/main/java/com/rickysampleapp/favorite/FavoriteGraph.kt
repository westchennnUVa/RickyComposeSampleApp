package com.rickysampleapp.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable object FavoriteBaseRoute

// TODO top level destination, navigation config and options
fun NavController.navigateToFavoriteGraph() =
    navigate(FavoriteBaseRoute)

fun NavGraphBuilder.favoriteGraph() {
    navigation<FavoriteBaseRoute>(
        startDestination = FavoriteRoute
    ) {
        favoriteScreen()
    }
}