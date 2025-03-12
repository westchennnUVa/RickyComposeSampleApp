package com.rickysampleapp.data.repository

import android.util.Log
import com.example.rickysampleapp.network.RickyNetworkDataSource
import com.example.rickysampleapp.network.dto.toModel
import com.example.rickysampleapp.network.utils.NetworkResponse
import com.rickeysampleapp.model.CharacterDetailModel
import com.rickeysampleapp.model.CharacterModel
import com.rickeysampleapp.model.PaginationInfoModel
import com.rickysampleapp.common.Dispatcher
import com.rickysampleapp.common.RickyDispatchers
import com.rickysampleapp.common.model.PaginationInfoType
import com.rickysampleapp.data.model.asEntity
import com.rickysampleapp.database.dao.CharacterDao
import com.rickysampleapp.database.dao.CharacterDetailDao
import com.rickysampleapp.database.dao.PaginationInfoDao
import com.rickysampleapp.database.entity.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataSource: RickyNetworkDataSource,
    private val characterDao: CharacterDao, // todo make it local data source
    private val characterDetailDao: CharacterDetailDao, // todo make it local data source
    private val paginationInfoDao: PaginationInfoDao,
    @Dispatcher(RickyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): CharacterRepository {

    // todo - this now is using one-time suspend query,
    //  think about for this case which should not be changed frequently, should we use flow or one-time suspend method?
    override suspend fun getCharacterDetail(
        id: Int,
        onStart: () -> Unit,
        onComplete: (CharacterDetailModel) -> Unit,
        onError: (String?) -> Unit
    ): Unit = withContext(ioDispatcher) {
        onStart()
        val characterDetailEntity = characterDetailDao.getCharacterDetail(id)
        if (characterDetailEntity == null) {
            networkDataSource.getCharacter(id).mapResult { characterDetailDto ->
                characterDetailDto.asEntity()
            }.suspendOnSuccess { fetchedCharacterDetailEntity ->
                characterDetailDao.insertCharacterDetail(fetchedCharacterDetailEntity)
                characterDetailDao.getCharacterDetail(id)?.let {
                    onComplete(it.toModel())
                } ?: run {
                    onError("null result from DB")
                }
            }.onFailure { exception ->
                onError(exception.message)
            }
        } else {
            onComplete(characterDetailEntity.toModel())
        }
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