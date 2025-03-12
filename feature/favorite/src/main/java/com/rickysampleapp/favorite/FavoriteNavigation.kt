package com.rickysampleapp.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable object FavoriteRoute

fun NavController.navigateToFavorite() =
    navigate(FavoriteRoute)

fun NavGraphBuilder.favoriteScreen() {
    composable<FavoriteRoute> {
        FavoriteRoute()
    }
}