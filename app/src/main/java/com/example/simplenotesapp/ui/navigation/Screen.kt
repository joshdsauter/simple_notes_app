package com.example.simplenotesapp.ui.navigation

sealed class Screen(val route: String) {
    object NotesHome : Screen("notes_home")
    object NoteEditor : Screen("note_editor")
}