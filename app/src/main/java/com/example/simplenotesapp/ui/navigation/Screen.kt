package com.example.simplenotesapp.ui.navigation

sealed class Screen(val route: String) {
    object NotesHome : Screen("notes_home")
    object NoteView: Screen("note_view")
    object NoteEditor : Screen("note_editor")
}