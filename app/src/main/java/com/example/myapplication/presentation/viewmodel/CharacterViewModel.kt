package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.util.Resource
import com.example.myapplication.domain.usecase.GetCharacterUseCase
import com.example.myapplication.domain.usecase.GetCharactersUseCase
import com.example.myapplication.presentation.state.CharacterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    init {
        loadCharacters(1)
    }

    fun loadCharacters(page: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            when (val result = getCharactersUseCase(page)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        characters = result.data.characters,
                        currentPage = result.data.currentPage,
                        totalPages = result.data.totalPages
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.error
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, characters = emptyList())
            when (val result = getCharacterUseCase(id)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        character = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.error
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }
}
