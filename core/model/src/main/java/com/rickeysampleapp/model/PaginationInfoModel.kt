package com.rickeysampleapp.model

import com.rickysampleapp.common.model.PaginationInfo
import com.rickysampleapp.common.model.PaginationInfoType

data class PaginationInfoModel(
    val type: PaginationInfoType?,
    val count: Int = PaginationInfo.NO_TOTAL_COUNT,
    val pages: Int = PaginationInfo.NO_TOTAL_PAGE,
    val next: String? = null,
    val prev: String? = null
)