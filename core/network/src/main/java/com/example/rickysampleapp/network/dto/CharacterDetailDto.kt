package com.example.rickysampleapp.network.dto

import android.annotation.SuppressLint
import com.rickeysampleapp.model.CharacterDetailModel
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CharacterDetailDto(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val episode: List<String>?,
    val image: String?,
    val created: String?
)

@SuppressLint("NewApi")
fun CharacterDetailDto.toModel() = CharacterDetailModel(
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
