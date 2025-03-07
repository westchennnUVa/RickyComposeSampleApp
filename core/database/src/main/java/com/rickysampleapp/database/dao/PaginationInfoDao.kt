package com.rickysampleapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rickysampleapp.common.model.PaginationInfoType
import com.rickysampleapp.database.entity.PaginationInfoEntity

@Dao
interface PaginationInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaginationInfo(paginationInfo: PaginationInfoEntity)

    @Query("SELECT * FROM PaginationInfoEntity WHERE type = :type")
    suspend fun getPaginationInfo(type: PaginationInfoType): PaginationInfoEntity

}