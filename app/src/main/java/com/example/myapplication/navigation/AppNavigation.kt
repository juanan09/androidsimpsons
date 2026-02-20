package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.presentation.ui.CharacterDetailScreen
import com.example.myapplication.presentation.ui.CharactersScreen
import com.example.myapplication.presentation.viewmodel.CharacterViewModel

object AppDestinations {
    const val CHARACTERS_LIST = "charactersList"
    const val CHARACTER_DETAIL = "characterDetail/{characterId}"
}

@Composable
fun AppNavigation(viewModel: CharacterViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.CHARACTERS_LIST
    ) {
        composable(AppDestinations.CHARACTERS_LIST) {
            CharactersScreen(
                viewModel = viewModel,
                onCharacterClick = { characterId ->
                    navController.navigate(AppDestinations.CHARACTER_DETAIL.replace("{characterId}", characterId.toString()))
                }
            )
        }

        composable(
            route = AppDestinations.CHARACTER_DETAIL,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailScreen(
                characterId = characterId,
                viewModel = viewModel,
                onBack = { 
                    viewModel.resetSearch() // Limpiamos la búsqueda antes de volver
                    navController.popBackStack() 
                }
            )
        }
    }
}
