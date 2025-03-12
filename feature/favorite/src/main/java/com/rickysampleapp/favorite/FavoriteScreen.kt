package com.rickysampleapp.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoriteRoute(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    FavoriteScreen(modifier = modifier)
}

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    Text("Favorite")
}