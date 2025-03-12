package com.rickysampleapp.database.entity

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rickeysampleapp.model.CharacterDetailModel

@Entity
data class CharacterDetailEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val episode: List<String>?,
    val image: String?,
    val created: Long?
)

@SuppressLint("NewApi")
fun CharacterDetailEntity.toModel() = CharacterDetailModel(
    id = id,
    name = name ?: "no name",
    status = status ?: "no status",
    species = species ?: "no species",
    type = type ?: "no type",
    gender = gender ?: "no gender",
    episode = episode ?: emptyList(),
    image = image ?: "no image",
    created = created ?: -1L
)