package com.rickysampleapp.database.entity

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rickeysampleapp.model.CharacterModel

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val episode: List<String>?,
    val image: String?,
    val created: Long?,
    var page: Int = 0,
)

@SuppressLint("NewApi")
fun CharacterEntity.toModel() = CharacterModel(
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

data class CharactersEntity(
    val info: PaginationInfoEntity?,
    val results: List<CharacterEntity>?
)