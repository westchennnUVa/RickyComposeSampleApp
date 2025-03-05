package com.rickysampleapp.data.repository

import com.example.rickysampleapp.network.RickyNetworkDataSource
import com.example.rickysampleapp.network.dto.toModel
import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterModel
import com.rickeysampleapp.model.CharactersModel
import com.rickysampleapp.common.Dispatcher
import com.rickysampleapp.common.RickyDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataSource: RickyNetworkDataSource,
    @Dispatcher(RickyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): CharacterRepository {

    override suspend fun getCharacter(id: Int): NetworkResponse<CharacterModel> =
        networkDataSource.getCharacter(id)
            .mapResult {
                it.toModel()
            }

    override fun getCharacters(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
        // todo this is still return model to UI layer, might need to adjust
    ): Flow<CharactersModel> = flow {
        // TODO bring ROOM DAO here for offline first impl
        networkDataSource.getCharacters(page).mapResult {
                it.toModel()
            }.suspendOnSuccess { characters ->
                emit(characters)
            }.onFailure { exception ->
                onError(exception.message)
            }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}