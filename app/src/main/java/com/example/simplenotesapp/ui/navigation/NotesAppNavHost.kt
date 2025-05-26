package com.example.simplenotesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplenotesapp.ui.screens.NoteEditorScreen
import com.example.simplenotesapp.ui.screens.NotesHomeScreen

@Composable
fun NotesAppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.NotesHome.route) {
        composable(Screen.NotesHome.route) {
            NotesHomeScreen(
                onAddNote = { navController.navigate(Screen.NoteEditor.route) }
            )
        }

        composable(Screen.NoteEditor.route) {
            NoteEditorScreen(
                onSave = { title, content ->
                    // Save logic here (you could pop back for now)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}
