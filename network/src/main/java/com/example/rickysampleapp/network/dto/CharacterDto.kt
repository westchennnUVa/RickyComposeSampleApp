package com.example.rickysampleapp.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
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

// TODO mapper