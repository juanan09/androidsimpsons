package com.example.myapplication.domain.usecase

import com.example.myapplication.core.util.Resource
import com.example.myapplication.domain.model.Character
import com.example.myapplication.domain.repository.SimpsonsRepository

class GetCharacterUseCase(private val repository: SimpsonsRepository) {
    suspend operator fun invoke(id: Int): Resource<Character> {
        return repository.getCharacter(id)
    }
}
