package com.rickysampleapp.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    // TODO do we have lifecycle aware one?
    val characterUiState by viewModel.stateFlow.collectAsState()

    Surface(modifier = modifier) {
        when (val uiState = characterUiState) {
            is CharacterDetailUiState.Loading -> Text("Loading")
            is CharacterDetailUiState.Error -> Text("exception message is: ${uiState.message}")
            is CharacterDetailUiState.Idle -> {
                Column(modifier.fillMaxSize()) {
                    Text("ID: ${uiState.character.id}")
                    Text("Name: ${uiState.character.name}")
                    Text("Status: ${uiState.character.status}")
                    Text("Species: ${uiState.character.species}")
                    Text("Type: ${uiState.character.type}")
                    Text("Gender: ${uiState.character.gender}")
                    Text("Episodes: ${uiState.character.episode}")
                    Text("Created: ${uiState.character.created}")
                    AsyncImage(
                        model = uiState.character.image,
                        contentDescription = null
                    )
                }
            }
        }
    }

}