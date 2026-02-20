package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.CharacterDto
import com.example.myapplication.data.remote.dto.CharacterListDto
import com.example.myapplication.domain.model.Character
import com.example.myapplication.domain.model.CharacterList

fun CharacterDto.toDomain(): Character {
    // La API devuelve portrait_path como "/character/1.webp"
    // Según la estructura, la URL completa es https://thesimpsonsapi.com/storage/character/1.webp
    val path = this.portraitPath ?: ""
    val fullImageUrl = if (path.startsWith("/")) {
        "https://cdn.thesimpsonsapi.com/500$path"
    } else if (path.isNotEmpty()) {
        "https://cdn.thesimpsonsapi.com/500$path"
    } else {
        ""
    }

    return Character(
        id = this.id,
        name = this.name,
        age = this.age?.toString() ?: "Unknown",
        gender = this.gender ?: "Unknown",
        occupation = this.occupation ?: "Unknown",
        imageUrl = fullImageUrl,
        phrases = this.phrases ?: emptyList()
    )
}

fun CharacterListDto.toDomain(): CharacterList {
    return CharacterList(
        characters = this.results.map { it.toDomain() },
        currentPage = extractPageNumber(this.next, this.prev),
        totalPages = this.pages,
        totalCharacters = this.count
    )
}

private fun extractPageNumber(next: String?, prev: String?): Int {
    return when {
        next != null -> {
            val pageStr = next.substringAfter("page=", "")
            (pageStr.toIntOrNull() ?: 2) - 1
        }
        prev != null -> {
            val pageStr = prev.substringAfter("page=", "")
            (pageStr.toIntOrNull() ?: 0) + 1
        }
        else -> 1
    }
}
