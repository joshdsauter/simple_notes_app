package com.example.simplenotesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.getValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.ui.components.NoteItem
import com.example.simplenotesapp.viewmodel.NotesViewModel
import com.example.simplenotesapp.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesHomeScreen(
    onAddNote: () -> Unit = {},
    onNoteEdit: (Note) -> Unit = {},
    onNoteView: (Int) -> Unit = {},
    viewModel: NotesViewModel = viewModel(),
    themeViewModel: ThemeViewModel
) {
    val notes = viewModel.notes // ← ViewModel state
    val isDarkMode by themeViewModel.isDarkMode.collectAsState() // 👈 collect theme state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Notes") },
                actions = {
                    IconButton(onClick = { themeViewModel.toggleTheme() }) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.LightMode  else Icons.Default.DarkMode,
                            contentDescription = "Toggle Theme"
                        )
                    }
                }
            )
        },
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
            val searchQuery by viewModel.searchQuery

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Search notes...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                singleLine = true,
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                }
            )

            if (notes.value.isEmpty()) {
                Text(
                    text = "No notes yet. Tap + to create one!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 32.dp)
                )
            } else {
                LazyColumn {
                    items(notes.value) { note ->
                        NoteItem(
                            note = note,
                            onClick = { onNoteView(note.id) },
                            onDelete = { viewModel.deleteNote(it) },
                            onEditClick = { onNoteEdit(note) }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

            }
        }
    }
}