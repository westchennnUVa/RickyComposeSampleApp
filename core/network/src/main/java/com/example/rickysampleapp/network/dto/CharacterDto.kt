package com.example.rickysampleapp.network.dto

import android.annotation.SuppressLint
import com.rickeysampleapp.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int?,
    val name: String?,
    val image: String?,
)

@SuppressLint("NewApi")
fun CharacterDto.toModel() = CharacterModel(
    id = id ?: -1,
    name = name ?: "no name",
    image = image ?: "no image",
)