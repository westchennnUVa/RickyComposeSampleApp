package com.rickysampleapp.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.rickeysampleapp.model.CharacterModel

@Composable
fun CharacterCard(
    characterModel: CharacterModel,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = characterModel.image,
                contentDescription = null
            )
            Text(text = characterModel.name)
        }
    }
}

@Preview
@Composable
private fun CharacterCardPreview() {
    CharacterCard(
        CharacterModel(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            episode = emptyList(),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            created = 111
        )
    )
}