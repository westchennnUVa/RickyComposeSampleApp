package com.rickysampleapp.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.rickysampleapp.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable object HomeBaseRoute

// TODO top level destination, navigation config and options
fun NavController.navigateToHomeGraph() =
    navigate(HomeBaseRoute)

fun NavGraphBuilder.homeGraph(onCharacterClicked: (Int) -> Unit) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        homeScreen(onCharacterClicked = onCharacterClicked)
    }
}