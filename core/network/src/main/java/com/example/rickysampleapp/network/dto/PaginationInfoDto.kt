package com.example.rickysampleapp.network.dto

import com.rickeysampleapp.model.PaginationInfoModel
import com.rickysampleapp.common.model.PaginationInfo
import com.rickysampleapp.common.model.PaginationInfoType
import kotlinx.serialization.Serializable

@Serializable
data class PaginationInfoDto(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?
)

fun PaginationInfoDto.toModel(type: PaginationInfoType): PaginationInfoModel = PaginationInfoModel(
    type = type,
    count = count ?: PaginationInfo.NO_TOTAL_COUNT,
    pages = pages ?: PaginationInfo.NO_TOTAL_PAGE,
    next = next,
    prev = prev
)