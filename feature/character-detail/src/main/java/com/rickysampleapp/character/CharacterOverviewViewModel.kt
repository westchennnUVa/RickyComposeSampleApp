package com.rickysampleapp.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickeysampleapp.model.CharacterModel
import com.rickysampleapp.data.repository.CharacterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterOverviewViewModel @Inject constructor(
    private val characterRepositoryImpl: CharacterRepositoryImpl
): ViewModel() {

    private val _stateflow = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val stateFlow = _stateflow.asStateFlow()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            characterRepositoryImpl
                .getCharacter(id)
                // todo where and how to map it to UI Model? If it's necessary?
                // .mapResult { it }
                .onSuccess { character ->
                    _stateflow.update { CharacterUiState.Success(character) }
                }.onFailure { exception ->
                    _stateflow.update { CharacterUiState.Failure(exception.message ?: "unknown exception") }
                }
        }
    }

}

sealed interface CharacterUiState {
    data object Loading : CharacterUiState
    // todo where and how to map it to UI Model? If it's necessary? Now it's domain model
    data class Success(val character: CharacterModel) : CharacterUiState
    data class Failure(val message: String) : CharacterUiState
}