package com.example.rickysampleapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.rickysampleapp.favorite.FavoriteBaseRoute
import com.rickysampleapp.favorite.FavoriteRoute
import com.rickysampleapp.home.navigation.HomeBaseRoute
import com.rickysampleapp.home.navigation.HomeRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val icon: ImageVector,
    val contentDescription: String,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        icon = Icons.Default.Home,
        contentDescription = "Home",
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class
    ),
    FAVORITE(
        icon = Icons.Default.Favorite,
        contentDescription = "Favorite",
        route = FavoriteRoute::class,
        baseRoute = FavoriteBaseRoute::class
    )


}