package com.rickysampleapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rickeysampleapp.model.CharacterDetailModel
import com.rickeysampleapp.model.CharacterModel

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val image: String?,
    var page: Int = 0,
)

data class CharactersEntity(
    val info: PaginationInfoEntity?,
    val results: List<CharacterEntity>?
)

fun CharacterEntity.toModel() = CharacterModel(
    id = id,
    name = name ?: "no name",
    image = image ?: "no image",
)