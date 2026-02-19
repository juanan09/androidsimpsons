package com.example.myapplication.domain.repository

import com.example.myapplication.core.util.Resource
import com.example.myapplication.domain.model.Character

interface SimpsonsRepository {
    suspend fun getCharacter(id: Int): Resource<Character>
}
