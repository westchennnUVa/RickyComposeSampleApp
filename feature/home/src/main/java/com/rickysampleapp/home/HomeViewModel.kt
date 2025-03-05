package com.rickysampleapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickeysampleapp.model.CharacterModel
import com.rickysampleapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    internal val uiState = _uiState.asStateFlow()

    // TODO to think about how to prevent loading not exist page when reach the limit by using either nextPageUrl or the total count
    private val _nextPageUrl = MutableStateFlow<String?>(null)

    private val _page = MutableStateFlow(1)
    internal val characterList: StateFlow<List<CharacterModel>> = _page.flatMapLatest { nextPage -> // todo more understanding of
        characterRepository.getCharacters(
            nextPage,
            onStart = { _uiState.tryEmit(HomeUiState.Loading) }, // TODO check tryEmit vs. emit
            onComplete = { _uiState.tryEmit(HomeUiState.Idle) },
            onError = { message -> _uiState.tryEmit(HomeUiState.Error(message)) }
        ).map { charatersModel ->
            charatersModel.characters
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )


}

internal sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Idle : HomeUiState
    data class Error(val message: String?) : HomeUiState
}