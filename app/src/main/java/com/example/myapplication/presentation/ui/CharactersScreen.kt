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
import com.example.myapplication.presentation.viewmodel.CharacterViewModel

@Composable
fun CharactersScreen(viewModel: CharacterViewModel) {
    val state by viewModel.state.collectAsState()
    var searchId by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1A1A),
        topBar = {
            Column(modifier = Modifier.background(Color(0xFF1A1A1A))) {
                Spacer(modifier = Modifier.height(32.dp))
                Searcher(
                    value = searchId,
                    onValueChange = { 
                        searchId = it
                        viewModel.onSearch(it)
                    },
                    placeholder = "Busca por nombre o ID..."
                )
            }
        },
        bottomBar = {
            // Solo mostramos la paginación si no hay un personaje específico buscado por ID
            if (state.character == null && state.characters.isNotEmpty()) {
                Surface(color = Color(0xFF2A2803)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
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
                // Si la búsqueda fue por ID, mostramos solo ese personaje
                Column(modifier = Modifier.fillMaxSize()) {
                    CharacterItem(
                        name = state.character!!.name,
                        quote = state.character!!.phrases.firstOrNull() ?: "D'oh!",
                        description = state.character!!.occupation,
                        imageUrl = state.character!!.imageUrl
                    )
                }
            } else {
                // Si no hay búsqueda por ID, mostramos la lista (que puede estar filtrada localmente)
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.characters) { character ->
                        CharacterItem(
                            name = character.name,
                            quote = character.phrases.firstOrNull() ?: "D'oh!",
                            description = character.occupation,
                            imageUrl = character.imageUrl
                        )
                    }
                }
            }

            state.error?.let { error ->
                val errorMessage = when (error) {
                    is AppError.HttpError -> "Personaje no encontrado (ID: $searchId)"
                    AppError.NoInternet -> "Sin conexión a internet"
                    AppError.Timeout -> "Tiempo de espera agotado"
                    is AppError.Unknown -> "Error: ${error.message}"
                }
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }
        }
    }
}
