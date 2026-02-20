package com.example.myapplication.domain.repository

import com.example.myapplication.core.util.Resource
import com.example.myapplication.domain.model.Character
import com.example.myapplication.domain.model.CharacterList

interface SimpsonsRepository {
    suspend fun getCharacter(id: Int): Resource<Character>
    suspend fun getCharacters(page: Int): Resource<CharacterList>
}
