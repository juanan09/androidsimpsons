package com.example.myapplication.domain.model

data class Character(
    val id: Int,
    val name: String,
    val age: String,
    val gender: String,
    val occupation: String,
    val imageUrl: String,
    val phrases: List<String>
)
