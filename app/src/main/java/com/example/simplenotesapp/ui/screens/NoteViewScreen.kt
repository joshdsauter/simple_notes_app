package com.example.simplenotesapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplenotesapp.data.model.Note
import com.example.simplenotesapp.ui.components.DeleteNoteDialog
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteViewScreen(
    note: Note,
    onDelete: (Note) -> Unit,
    onEdit: () -> Unit,
    onBack: () -> Unit,
    navController: NavController
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("View Note") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            val sdf = SimpleDateFormat("MMM dd, yyyy • h:mm a", Locale.getDefault())
            Text(
                text = "Last updated: ${sdf.format(Date(note.updatedAt))}",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Visible
            )
        }
    }
    if (showDeleteDialog) {
        DeleteNoteDialog(
            note = note,
            onConfirmDelete = {
                onDelete(it)
                navController.popBackStack()
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}
