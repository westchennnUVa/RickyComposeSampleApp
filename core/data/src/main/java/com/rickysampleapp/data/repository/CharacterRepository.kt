package com.rickysampleapp.data.repository

import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterDetailModel
import com.rickeysampleapp.model.CharacterModel
import com.rickeysampleapp.model.PaginationInfoModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacterDetail(
        id: Int,
        onStart: () -> Unit,
        onComplete: (CharacterDetailModel) -> Unit,
        onError: (String?) -> Unit
    )

    fun getCharacters(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Pair<PaginationInfoModel, List<CharacterModel>>>
}