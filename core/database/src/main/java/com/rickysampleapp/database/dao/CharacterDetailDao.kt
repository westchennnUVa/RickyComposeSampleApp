package com.rickysampleapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rickysampleapp.database.entity.CharacterDetailEntity

@Dao
interface CharacterDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterDetail(characterDetailEntity: CharacterDetailEntity)

    @Query("SELECT * FROM CharacterDetailEntity WHERE id = :id")
    fun getCharacterDetail(id: Int): CharacterDetailEntity?
}