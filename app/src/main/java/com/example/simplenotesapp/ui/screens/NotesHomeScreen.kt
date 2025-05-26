package com.example.simplenotesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.ui.components.NoteItem
import com.example.simplenotesapp.viewmodel.NotesViewModel

@Composable
fun NotesHomeScreen(
    onAddNote: () -> Unit = {},
    viewModel: NotesViewModel = viewModel()
) {
    val notes = viewModel.notes // ← ViewModel state
    // 🔹 Sample notes for now
    val sampleNotes = listOf(
        Note(1, "Grocery List", "Eggs, Milk, Bread, and Butter"),
        Note(2, "Workout Plan", "Push-ups, Squats, 20-min run"),
        Note(3, "Ideas", "Build a note-taking app, read a book, start journaling")
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Your Notes",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(notes) { note ->
                    NoteItem(note = note)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}