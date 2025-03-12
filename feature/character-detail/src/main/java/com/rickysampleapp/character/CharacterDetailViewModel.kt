package com.rickysampleapp.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rickeysampleapp.model.CharacterDetailModel
import com.rickysampleapp.character.navigation.CharacterDetailRoute
import com.rickysampleapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository
): ViewModel() {

    // todo learn more about savedStateHandle
    private val characterId = savedStateHandle.toRoute<CharacterDetailRoute>().characterId

    private val _stateflow = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val stateFlow = _stateflow.asStateFlow()

    init {
        viewModelScope.launch {
            characterRepository.getCharacterDetail(
                id = characterId,
                onStart = { _stateflow.tryEmit(CharacterDetailUiState.Loading) },
                onComplete = { characterDetailModel -> _stateflow.tryEmit(CharacterDetailUiState.Idle(characterDetailModel))  },
                onError = { message -> _stateflow.tryEmit(CharacterDetailUiState.Error(message)) }
            )
        }
    }
}

sealed interface CharacterDetailUiState {
    data object Loading : CharacterDetailUiState
    data class Idle(val character: CharacterDetailModel) : CharacterDetailUiState
    data class Error(val message: String?) : CharacterDetailUiState
}