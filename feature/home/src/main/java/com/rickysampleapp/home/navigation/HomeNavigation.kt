package com.rickysampleapp.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rickysampleapp.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(onCharacterClicked: (Int) -> Unit) {
    composable<HomeRoute> {
        HomeScreen(onCharacterClicked = onCharacterClicked)
    }
}