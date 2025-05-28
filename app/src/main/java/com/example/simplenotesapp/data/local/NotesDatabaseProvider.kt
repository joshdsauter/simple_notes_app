package com.example.simplenotesapp.data.local

import android.content.Context
import androidx.room.Room

object NotesDatabaseProvider {
    @Volatile
    private var INSTANCE: NotesDatabase? = null

    fun getDatabase(context: Context): NotesDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                "notes-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}