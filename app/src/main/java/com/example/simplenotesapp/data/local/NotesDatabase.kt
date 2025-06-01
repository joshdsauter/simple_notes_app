package com.example.simplenotesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplenotesapp.data.model.Note

@Database(entities = [Note::class], version = 2)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}