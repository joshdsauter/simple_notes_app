package com.example.simplenotesapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.simplenotesapp.data.model.Note

@Composable
fun DeleteNoteDialog(
    note: Note,
    onConfirmDelete: (Note) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Note") },
        text = { Text("Are you sure you want to delete this note? This action cannot be undone.") },
        confirmButton = {
            TextButton(onClick = {
                onConfirmDelete(note)
                onDismiss()
            }) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
