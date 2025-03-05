package com.rickeysampleapp.model

data class CharactersModel(
    val next: String,
    val characters: List<CharacterModel>
) {
    companion object {
        const val NO_NEXT_PAGE = "noNextPage"
    }
}