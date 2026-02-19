package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.CharacterDto
import com.example.myapplication.domain.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(
        id = this.id,
        name = this.name,
        age = this.age ?: "Unknown",
        gender = this.gender ?: "Unknown",
        occupation = this.occupation ?: "Unknown",
        imageUrl = this.portraitPath ?: "",
        phrases = this.phrases ?: emptyList()
    )
}
