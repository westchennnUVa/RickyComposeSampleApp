package com.rickysampleapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rickysampleapp.database.dao.CharacterDao
import com.rickysampleapp.database.dao.CharacterDetailDao
import com.rickysampleapp.database.dao.PaginationInfoDao
import com.rickysampleapp.database.entity.CharacterDetailEntity
import com.rickysampleapp.database.entity.CharacterEntity
import com.rickysampleapp.database.entity.PaginationInfoEntity

@Database(
    entities = [CharacterEntity::class,
                CharacterDetailEntity::class,
               PaginationInfoEntity::class],
    version = 1,
)
@TypeConverters(RickyTypeConverters::class)
abstract class RickyDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun characterDetailDao(): CharacterDetailDao

    abstract fun paginationInfoDao(): PaginationInfoDao

}