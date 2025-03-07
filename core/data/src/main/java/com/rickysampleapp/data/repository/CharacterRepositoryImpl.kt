package com.rickysampleapp.data.repository

import android.util.Log
import com.example.rickysampleapp.network.RickyNetworkDataSource
import com.example.rickysampleapp.network.dto.CharacterDto
import com.example.rickysampleapp.network.dto.CharactersDto
import com.example.rickysampleapp.network.dto.toModel
import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterModel
import com.rickeysampleapp.model.CharactersModel
import com.rickeysampleapp.model.PaginationInfoModel
import com.rickysampleapp.common.Dispatcher
import com.rickysampleapp.common.RickyDispatchers
import com.rickysampleapp.common.model.PaginationInfoType
import com.rickysampleapp.data.model.asEntity
import com.rickysampleapp.database.dao.CharacterDao
import com.rickysampleapp.database.dao.PaginationInfoDao
import com.rickysampleapp.database.entity.CharacterEntity
import com.rickysampleapp.database.entity.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataSource: RickyNetworkDataSource,
    private val characterDao: CharacterDao, // todo make it local data source
    private val paginationInfoDao: PaginationInfoDao,
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
        // TODO 1. how to always return the same flow, not re-create new one for each invoke?
    ): Flow<Pair<PaginationInfoModel, List<CharacterModel>>> = flow {
        val characterEntities = characterDao.getCharacterFromPage(page)
        if (characterEntities.isEmpty()) {
            networkDataSource.getCharacters(page).mapResult { charactersDto ->
                charactersDto.asEntity(PaginationInfoType.Character)
            }.suspendOnSuccess { charactersEntity ->
                val newCharactersEntities = charactersEntity.results?.map { it.copy(page = page) } ?: emptyList()
                if (newCharactersEntities.isEmpty()) {
                    return@suspendOnSuccess
                }
                // insert
                characterDao.insertAllCharacters(newCharactersEntities)
                charactersEntity.info?.let { paginationInfoDao.insertPaginationInfo(it) }
                // get
                val characters = characterDao.getAllCharacters(page).map { it.toModel() }
                val characterPaginationInfoModel = paginationInfoDao.getPaginationInfo(PaginationInfoType.Character).toModel()
                Log.d("TESTTESTTEST", "first save from network with size ${newCharactersEntities.size} and get from DB with size ${characters.size}, and for pagination total pages ${characterPaginationInfoModel.pages}")
                emit(characterPaginationInfoModel to characters)
            }.onFailure { exception ->
                onError(exception.message)
            }
        } else {
            val characters = characterDao.getAllCharacters(page).map { it.toModel() }
            val characterPaginationInfoModel = paginationInfoDao.getPaginationInfo(PaginationInfoType.Character).toModel()
            Log.d("TESTTESTTEST", "directly get from DB with size ${characters.size}, and for pagination total pages ${characterPaginationInfoModel.pages}")
            emit(characterPaginationInfoModel to characters)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}