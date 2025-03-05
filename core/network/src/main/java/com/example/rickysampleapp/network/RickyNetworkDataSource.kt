package com.example.rickysampleapp.network

import com.example.rickysampleapp.network.dto.CharacterDto
import com.example.rickysampleapp.network.dto.CharactersDto
import com.example.rickysampleapp.network.utils.NetworkResponse

interface RickyNetworkDataSource {

    suspend fun getCharacter(id: Int): NetworkResponse<CharacterDto>

    suspend fun getCharacters(page: Int): NetworkResponse<CharactersDto>
}