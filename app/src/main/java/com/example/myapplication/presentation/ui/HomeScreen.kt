package com.example.myapplication.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.CharacterItem
import com.example.myapplication.components.Searcher
import com.example.myapplication.core.error.AppError
import com.example.myapplication.presentation.viewmodel.CharacterViewModel

@Composable
fun HomeScreen(viewModel: CharacterViewModel) {
    val state by viewModel.state.collectAsState()
    var searchId by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1A1A)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Searcher(
                value = searchId,
                onValueChange = { searchId = it },
                placeholder = "Enter Character ID (e.g. 1)"
            )
            
            Button(
                onClick = { 
                    searchId.toIntOrNull()?.let { viewModel.getCharacter(it) } 
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("Search Character")
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Yellow
                    )
                }

                state.character?.let { char ->
                    CharacterItem(
                        name = char.name,
                        quote = char.phrases.firstOrNull() ?: "No phrases found",
                        description = char.occupation,
                        imageUrl = char.imageUrl
                    )
                }

                state.error?.let { error ->
                    val errorMessage = when(error) {
                        AppError.NoInternet -> "No internet connection"
                        AppError.Timeout -> "Request timed out"
                        is AppError.HttpError -> "Server error: ${error.code}"
                        is AppError.Unknown -> "An error occurred: ${error.message}"
                    }
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
