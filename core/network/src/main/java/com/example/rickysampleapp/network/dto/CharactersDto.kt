package com.example.rickysampleapp.network.dto

import com.rickeysampleapp.model.CharactersModel
import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto(
    val info: InfoDto?,
    val results: List<CharacterDto>?
)

@Serializable
data class InfoDto(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?
)

fun CharactersDto.toModel(): CharactersModel = CharactersModel(
    next = info?.next ?: CharactersModel.NO_NEXT_PAGE,
    characters = results?.map { it.toModel() } ?: emptyList()
)