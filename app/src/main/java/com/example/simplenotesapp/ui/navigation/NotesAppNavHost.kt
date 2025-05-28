package com.example.simplenotesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.ui.screens.NoteEditorScreen
import com.example.simplenotesapp.ui.screens.NotesHomeScreen
import com.example.simplenotesapp.viewmodel.NotesViewModel

@Composable
fun NotesAppNavHost() {
    val navController = rememberNavController()
    val viewModel: NotesViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.NotesHome.route) {
        composable(Screen.NotesHome.route) {
            NotesHomeScreen(
                onAddNote = { navController.navigate(Screen.NoteEditor.route) },
                viewModel = viewModel
            )
        }

        composable(Screen.NoteEditor.route) {
            NoteEditorScreen(
                onSave = { title, content ->
                    val newNote = Note(
                        id = viewModel.getNextId(),
                        title = title,
                        content = content
                    )
                    viewModel.addNote(newNote)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}


