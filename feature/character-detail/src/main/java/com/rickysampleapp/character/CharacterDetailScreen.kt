package com.rickysampleapp.character

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    modifier: Modifier = Modifier,
    viewModel: CharacterOverviewViewModel = hiltViewModel()
) {
    // TODO do we have lifecycle aware one?
    val characterUiState by viewModel.stateFlow.collectAsState()

    // TODO I don't think this should be like this using LaunchedEffect, need to adjust
    LaunchedEffect(Unit) {
        viewModel.getCharacter(characterId)
    }
    Surface(modifier = modifier) {
        when (val uiState = characterUiState) {
            is CharacterUiState.Loading -> Text("Loading")
            is CharacterUiState.Success -> Text(uiState.character.name)
            is CharacterUiState.Failure -> Text("exception message is: ${uiState.message}")
        }
    }

}