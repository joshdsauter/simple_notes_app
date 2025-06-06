package com.example.simplenotesapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.viewModelScope
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.data.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NotesRepository(application)
    private val _notes = mutableStateOf<List<Note>>(emptyList())
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    val notes: State<List<Note>> = derivedStateOf {
        val query = _searchQuery.value.lowercase()
        _notes.value.filter {
            it.title.lowercase().contains(query) || it.content.lowercase().contains(query)
        }
    }

    init {
        viewModelScope.launch {
            repository.getAllNotes.collect { noteList ->
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

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}