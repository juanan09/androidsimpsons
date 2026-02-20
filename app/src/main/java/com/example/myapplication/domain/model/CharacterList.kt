package com.example.myapplication.domain.model

data class CharacterList(
    val characters: List<Character>,
    val currentPage: Int,
    val totalPages: Int,
    val totalCharacters: Int
)
