package com.rickysampleapp.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rickysampleapp.home.components.CharacterCard
import com.rickysampleapp.home.utils.HomeConstants.THRESHOLD

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val characterList by homeViewModel.characterList.collectAsStateWithLifecycle()

    Surface(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(items = characterList, key = { _, character -> character.id }) { index, character ->
                if ((index + THRESHOLD) >= characterList.size && uiState != HomeUiState.Loading) {
                    homeViewModel.fetchNextCharacterList()
                }
                CharacterCard(character)
            }
        }
    }
}