package com.example.simplenotesapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.simplenotesapp.data.model.Note

class NotesViewModel : ViewModel() {
    // Internal state
    private val _notes = mutableStateListOf(
        Note(1, "Grocery List", "Eggs, Milk, Bread, and Butter"),
        Note(2, "Workout Plan", "Push-ups, Squats, 20-min run"),
        Note(3, "Ideas", "Build a note-taking app, read a book, start journaling")
    )

    // External immutable access
    val notes: List<Note> = _notes

    fun addNote(note: Note) {
        _notes.add(note)
    }

    fun deleteNote(note: Note) {
        _notes.remove(note)
    }

    // Later: updateNote(), etc.
}