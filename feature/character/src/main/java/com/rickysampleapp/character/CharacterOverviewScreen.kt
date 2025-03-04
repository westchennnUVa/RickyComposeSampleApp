package com.rickysampleapp.character

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterOverviewRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterOverviewViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacter(1)
    }

    Text(text = "TEST")
}