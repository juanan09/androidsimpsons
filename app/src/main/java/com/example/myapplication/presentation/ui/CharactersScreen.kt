package com.example.myapplication.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.core.error.AppError
import com.example.myapplication.presentation.ui.components.CharacterItem
import com.example.myapplication.presentation.ui.components.Searcher
import com.example.myapplication.presentation.ui.components.SimpsonHeader
import com.example.myapplication.presentation.viewmodel.CharacterViewModel
import com.example.myapplication.ui.theme.SearchBarBackground

@Composable
fun CharactersScreen(
    viewModel: CharacterViewModel,
    onCharacterClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    var searchId by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1A1A),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                // ENTE 1: ENCABEZADO
                SimpsonHeader()
                // ESPACIO DE SEPARACIÓN
                Spacer(modifier = Modifier.height(8.dp))

                // ENTE 2: BUSCADOR (con sus propios bordes redondeados)
                Searcher(
                    value = searchId,
                    onValueChange = { 
                        searchId = it
                        viewModel.onSearch(it)
                    },
                    placeholder = "Busca por nombre o ID...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        bottomBar = {
            if (state.character == null && state.characters.isNotEmpty()) {
                Surface(
                    color = SearchBarBackground,
                    tonalElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .navigationBarsPadding(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { viewModel.loadCharacters(state.currentPage - 1) },
                            enabled = state.currentPage > 1 && !state.isLoading
                        ) { Text("Anterior") }
                        
                        Text(
                            text = "Página ${state.currentPage} de ${state.totalPages}",
                            color = Color.White
                        )

                        Button(
                            onClick = { viewModel.loadCharacters(state.currentPage + 1) },
                            enabled = state.currentPage < state.totalPages && !state.isLoading
                        ) { Text("Siguiente") }
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Yellow
                )
            }

            if (state.character != null) {
                Column(modifier = Modifier.fillMaxSize()) {
                    CharacterItem(
                        name = state.character!!.name,
                        quote = state.character!!.phrases.firstOrNull() ?: "D'oh!",
                        description = state.character!!.occupation,
                        imageUrl = state.character!!.imageUrl,
                        onClick = { onCharacterClick(state.character!!.id) }
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(state.characters) { character ->
                        CharacterItem(
                            name = character.name,
                            quote = character.phrases.firstOrNull() ?: "D'oh!",
                            description = character.occupation,
                            imageUrl = character.imageUrl,
                            onClick = { onCharacterClick(character.id) }
                        )
                    }
                }
            }

            state.error?.let { error ->
                val errorMessage = when (error) {
                    is AppError.HttpError -> "Personaje no encontrado"
                    AppError.NoInternet -> "Sin conexión a internet"
                    AppError.Timeout -> "Tiempo de espera agotado"
                    is AppError.Unknown -> "Error inesperado"
                }
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
        }
    }
}
