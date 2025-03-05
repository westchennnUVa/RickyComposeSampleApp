package com.rickysampleapp.data.repository

import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterModel
import com.rickeysampleapp.model.CharactersModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacter(id: Int): NetworkResponse<CharacterModel>

    fun getCharacters(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<CharactersModel>
}