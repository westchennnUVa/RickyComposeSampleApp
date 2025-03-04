package com.rickysampleapp.data.repository

import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterModel

interface CharacterRepository {

    suspend fun getCharacter(id: Int): NetworkResponse<CharacterModel>

}