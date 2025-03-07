package com.rickysampleapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rickeysampleapp.model.PaginationInfoModel
import com.rickysampleapp.common.model.PaginationInfo
import com.rickysampleapp.common.model.PaginationInfoType

@Entity
data class PaginationInfoEntity(
    @PrimaryKey val type: PaginationInfoType = PaginationInfoType.Not_Set,
    val count: Int = PaginationInfo.NO_TOTAL_COUNT,
    val pages: Int = PaginationInfo.NO_TOTAL_PAGE,
    val next: String? = null,
    val prev: String? = null
)

fun PaginationInfoEntity.toModel() = PaginationInfoModel(
    type = type,
    count = count,
    pages = pages,
    next = next,
    prev = prev
)