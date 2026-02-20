package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.util.Resource
import com.example.myapplication.domain.model.Character
import com.example.myapplication.domain.usecase.GetCharacterUseCase
import com.example.myapplication.domain.usecase.GetCharactersUseCase
import com.example.myapplication.presentation.state.CharacterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    private var originalCharacters: List<Character> = emptyList()

    init {
        loadCharacters(1)
    }

    fun loadCharacters(page: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, character = null) }
            when (val result = getCharactersUseCase(page)) {
                is Resource.Success -> {
                    originalCharacters = result.data.characters
                    _state.update {
                        it.copy(
                            isLoading = false,
                            characters = originalCharacters,
                            currentPage = result.data.currentPage,
                            totalPages = result.data.totalPages
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.error) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun onSearch(query: String) {
        if (query.isEmpty()) {
            _state.update { it.copy(character = null, characters = originalCharacters, error = null) }
            return
        }

        val id = query.toIntOrNull()
        if (id != null) {
            getCharacter(id)
        } else {
            val filtered = originalCharacters.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _state.update { it.copy(character = null, characters = filtered, error = null) }
        }
    }

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getCharacterUseCase(id)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            character = result.data,
                            characters = emptyList()
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.error) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
