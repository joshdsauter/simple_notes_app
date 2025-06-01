package com.example.simplenotesapp.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.data.model.NotesViewModelFactory
import com.example.simplenotesapp.ui.screens.NoteEditorScreen
import com.example.simplenotesapp.ui.screens.NotesHomeScreen
import com.example.simplenotesapp.viewmodel.NotesViewModel

@Composable
fun NotesAppNavHost() {
    val navController = rememberNavController()
    val viewModel: NotesViewModel =
        viewModel(factory = NotesViewModelFactory(LocalContext.current.applicationContext as Application))

    NavHost(
        navController = navController,
        startDestination = Screen.NotesHome.route
    ) {
        composable(Screen.NotesHome.route) {
            NotesHomeScreen(
                viewModel = viewModel,
                onAddNote = { navController.navigate("${Screen.NoteEditor.route}/-1") },
                onNoteClick = { note ->
                    // Navigate with an ID → editor knows to load & edit an existing note
                    navController.navigate("${Screen.NoteEditor.route}/${note.id}")
                }
            )
        }

        composable(
            route = "${Screen.NoteEditor.route}/{noteId}",
            arguments = listOf(
                // noteId == -1 means “create new”; otherwise load from DB
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            // 1) Read the passed noteId (or -1 if none)
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1

            // 2) Try to find the existing note in ViewModel’s list.
            //    If noteId == -1 or not found, we’re in “create” mode; else “edit” mode.
            val existingNote: Note? =
                viewModel.notes.value.find { it.id == noteId }

            // 3) Extract initial values (empty or from DB)
            val initialTitle = existingNote?.title ?: ""
            val initialContent = existingNote?.content ?: ""
            val originalCreatedAt = existingNote?.createdAt ?: 0L

            NoteEditorScreen(
                initialTitle = initialTitle,
                initialContent = initialContent,
                onSave = { newTitle, newContent ->
                    if (noteId != -1 && existingNote != null) {
                        viewModel.updateNote(
                            id = existingNote.id,
                            title = newTitle,
                            content = newContent,
                            originalCreatedAt = originalCreatedAt
                        )
                    } else {
                        viewModel.addNote(
                            title = newTitle,
                            content = newContent
                        )
                    }
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}



