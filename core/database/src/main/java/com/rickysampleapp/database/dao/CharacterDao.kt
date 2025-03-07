package com.rickysampleapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rickysampleapp.database.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CHARACTERENTITY WHERE page = :page")
    suspend fun getCharacterFromPage(page: Int): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE page <= :page")
    suspend fun getAllCharacters(page: Int): List<CharacterEntity>
}