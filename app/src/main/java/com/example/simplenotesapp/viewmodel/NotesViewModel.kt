package com.example.simplenotesapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.data.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NotesRepository(application)
    private val _notes = mutableStateOf<List<Note>>(emptyList())
    val notes: State<List<Note>> = _notes

    init {
        viewModelScope.launch {
            repository.getAllNotes().collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun addNote(title: String, content: String) {
        val now = System.currentTimeMillis()
        val note = Note(
            title = title,
            content = content,
            createdAt = now,
            updatedAt = now
        )
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(id: Int, title: String, content: String, originalCreatedAt: Long) {
        val now = System.currentTimeMillis()
        val edited = Note(
            id = id,
            title = title,
            content = content,
            createdAt = originalCreatedAt,
            updatedAt = now
        )
        viewModelScope.launch {
            repository.updateNote(edited)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}