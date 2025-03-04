package com.rickysampleapp.data.repository

import com.example.rickysampleapp.network.RickyNetworkDataSource
import com.example.rickysampleapp.network.dto.toModel
import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterModel
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataSource: RickyNetworkDataSource
): CharacterRepository {

    override suspend fun getCharacter(id: Int): NetworkResponse<CharacterModel> =
        networkDataSource.getCharacter(id)
            .mapResult {
                it.toModel()
            }
}