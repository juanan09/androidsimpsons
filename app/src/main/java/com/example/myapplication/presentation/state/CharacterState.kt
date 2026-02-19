package com.example.myapplication.presentation.state

import com.example.myapplication.domain.model.Character
import com.example.myapplication.core.error.AppError

data class CharacterState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: AppError? = null
)
