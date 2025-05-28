package com.example.simplenotesapp.data.repository

import android.content.Context
import com.example.simplenotesapp.data.local.NotesDatabaseProvider
import com.example.simplenotesapp.data.model.Note


class NotesRepository(context: Context) {

    private val noteDao = NotesDatabaseProvider.getDatabase(context).noteDao()

    suspend fun addNote(note: Note) = noteDao.insertNote(note)
    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
}