package com.rickysampleapp.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickysampleapp.network.RickyNetworkDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterOverviewViewModel @Inject constructor(
    private val networkDataSource: RickyNetworkDataSource
): ViewModel() {

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            networkDataSource.getCharacter(id)
                .onSuccess { data ->
                    val test = data
                    Log.d("TESTTESTTEST", "character name is ${data.name}")
                }
        }
    }

}