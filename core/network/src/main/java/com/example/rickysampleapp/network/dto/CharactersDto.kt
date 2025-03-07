package com.example.rickysampleapp.network.dto

import com.rickeysampleapp.model.CharactersModel
import com.rickeysampleapp.model.PaginationInfoModel
import com.rickysampleapp.common.model.PaginationInfoType
import kotlinx.serialization.Serializable

// TODO maybe should not have this characterS Dto at the first place?
@Serializable
data class CharactersDto(
    val info: PaginationInfoDto?,
    val results: List<CharacterDto>?
)

fun CharactersDto.toModel(type: PaginationInfoType): CharactersModel = CharactersModel(
    info = info?.toModel(type) ?: PaginationInfoModel(type),
    characters = results?.map { it.toModel() } ?: emptyList()
)

