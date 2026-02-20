package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.core.network.NetworkHelper
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.data.repository.SimpsonsRepositoryImpl
import com.example.myapplication.domain.usecase.GetCharacterUseCase
import com.example.myapplication.domain.usecase.GetCharactersUseCase
import com.example.myapplication.presentation.ui.CharactersScreen
import com.example.myapplication.presentation.viewmodel.CharacterViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inyección de dependencias manual (obligatorio por arquitectura)
        val networkHelper = NetworkHelper(applicationContext)
        val api = RetrofitClient.api
        val repository = SimpsonsRepositoryImpl(api, networkHelper)
        
        // Instanciamos los dos casos de uso
        val getCharacterUseCase = GetCharacterUseCase(repository)
        val getCharactersUseCase = GetCharactersUseCase(repository)

        // Pasamos ambos al ViewModel
        val viewModel = CharacterViewModel(getCharacterUseCase, getCharactersUseCase)

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                CharactersScreen(viewModel = viewModel)
            }
        }
    }
}
