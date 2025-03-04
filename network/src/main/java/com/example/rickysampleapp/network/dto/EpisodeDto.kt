package com.example.rickysampleapp.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val id: Int?,
    val name: String?,
    val air_date: String?,
    val episode: String?,
    val created: String?
)

// TODO mapper
