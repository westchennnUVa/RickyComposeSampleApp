package com.rickysampleapp.data.model

import android.annotation.SuppressLint
import com.example.rickysampleapp.network.dto.CharacterDetailDto
import com.example.rickysampleapp.network.dto.CharacterDto
import com.example.rickysampleapp.network.dto.CharactersDto
import com.example.rickysampleapp.network.dto.PaginationInfoDto
import com.rickysampleapp.common.model.PaginationInfo
import com.rickysampleapp.common.model.PaginationInfoType
import com.rickysampleapp.database.entity.CharacterDetailEntity
import com.rickysampleapp.database.entity.CharacterEntity
import com.rickysampleapp.database.entity.CharactersEntity
import com.rickysampleapp.database.entity.PaginationInfoEntity
import java.time.Instant

@SuppressLint("NewApi")
fun CharacterDetailDto.asEntity() = CharacterDetailEntity(
    id = id ?: -1,
    name = name ?: "no name",
    status = status ?: "no status",
    species = species ?: "no species",
    type = type ?: "no type",
    gender = gender ?: "no gender",
    episode = episode ?: emptyList(),
    image = image ?: "no image",
    created = created?.let { Instant.parse(it).epochSecond } ?: -1L
)

fun CharacterDto.asEntity() = CharacterEntity(
    id = id ?: -1,
    name = name ?: "no name",
    image = image ?: "no image"
)

fun CharactersDto.asEntity(type: PaginationInfoType) = CharactersEntity(
    info = info?.asEntity(type) ?: PaginationInfoEntity(type),
    results = results?.map { it.asEntity() } ?: emptyList()
)

fun PaginationInfoDto.asEntity(type: PaginationInfoType) = PaginationInfoEntity(
    type  = type,
    count = count ?: PaginationInfo.NO_TOTAL_COUNT,
    pages = pages ?: PaginationInfo.NO_TOTAL_PAGE,
    next = next,
    prev = prev
)