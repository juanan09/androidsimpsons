package com.example.myapplication.presentation.state

import com.example.myapplication.domain.model.Character
import com.example.myapplication.core.error.AppError

data class CharacterState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val character: Character? = null,
    val currentPage: Int = 1,
    val totalPages: Int = 1,
    val error: AppError? = null
)
