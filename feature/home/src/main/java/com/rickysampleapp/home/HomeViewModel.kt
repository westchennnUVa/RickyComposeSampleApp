package com.rickysampleapp.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickeysampleapp.model.CharacterModel
import com.rickysampleapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    internal val uiState = _uiState.asStateFlow()

    private val _pageInfo = MutableStateFlow(CharacterPageInfo())
    internal val characterList: StateFlow<List<CharacterModel>> = _pageInfo
        .map { it.currentPage }
        .distinctUntilChanged()
        .flatMapLatest { nextPage -> // todo more understanding of
            val flow = characterRepository.getCharacters(
                nextPage,
                onStart = { _uiState.tryEmit(HomeUiState.Loading) }, // TODO check tryEmit vs. emit
                onComplete = { _uiState.tryEmit(HomeUiState.Idle) },
                onError = { message -> _uiState.tryEmit(HomeUiState.Error(message)) }
            )
            Log.d("TESTTESTTEST", "flow hash ${flow.hashCode()}")
            flow
        }.onEach { pagiInfoCharacterListPair ->
            if (_pageInfo.value.totalPage != pagiInfoCharacterListPair.first.pages) {
                _pageInfo.update { _pageInfo.value.copy(totalPage = pagiInfoCharacterListPair.first.pages) }
                Log.d("TESTTESTTEST", "page max update to ${_pageInfo.value.totalPage}")
            }
        }.map {
            it.second
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun fetchNextCharacterList() {
        if (uiState.value != HomeUiState.Loading && _pageInfo.value.canFetchNextPage()) {
            _pageInfo.update { it.copy(currentPage = it.currentPage + 1) }
            Log.d("TESTTESTTEST", "fetch new page ${_pageInfo.value.currentPage}")
        }
    }

}

internal sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Idle : HomeUiState
    data class Error(val message: String?) : HomeUiState
}

internal data class CharacterPageInfo(
    val currentPage: Int = 1,
    val totalPage: Int = Int.MAX_VALUE
) {
    fun canFetchNextPage(): Boolean = currentPage < totalPage
}