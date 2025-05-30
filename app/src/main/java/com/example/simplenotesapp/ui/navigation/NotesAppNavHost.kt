package com.example.simplenotesapp.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val viewModel: NotesViewModel = viewModel(factory = NotesViewModelFactory(LocalContext.current.applicationContext as Application))


    NavHost(navController = navController, startDestination = Screen.NotesHome.route) {
        composable(Screen.NotesHome.route) {
            NotesHomeScreen(
                onNoteClick = { note ->
                    navController.navigate(
                        "${Screen.NoteEditor.route}?id=${note.id}&title=${note.title}&content=${note.content}"
                    )
                },
                onAddNote = { navController.navigate(Screen.NoteEditor.route) },
                viewModel = viewModel
            )
        }

        composable(
            route = "${Screen.NoteEditor.route}?id={id}&title={title}&content={content}",
            arguments = listOf(
                navArgument("id") { defaultValue = -1 },
                navArgument("title") { defaultValue = "" },
                navArgument("content") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val content = backStackEntry.arguments?.getString("content") ?: ""

            NoteEditorScreen(
                initialTitle = title,
                initialContent = content,
                onSave = { newTitle, newContent ->
                    val editedNote = Note(
                        id = if (id != -1) id else 0, // or use 0 as fallback if creating
                        title = newTitle,
                        content = newContent
                    )
                    if (id != -1) {
                        viewModel.updateNote(editedNote) // You'll need to implement this
                    } else {
                        viewModel.addNote(editedNote)
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


