package com.rickeysampleapp.model

data class CharacterDetailModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val episode: List<String>,
    val image: String,
    val created: Long
)