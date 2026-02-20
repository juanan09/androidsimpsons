package com.example.myapplication.domain.usecase

import com.example.myapplication.core.util.Resource
import com.example.myapplication.domain.model.CharacterList
import com.example.myapplication.domain.repository.SimpsonsRepository

class GetCharactersUseCase(private val repository: SimpsonsRepository) {
    suspend operator fun invoke(page: Int): Resource<CharacterList> {
        return repository.getCharacters(page)
    }
}
