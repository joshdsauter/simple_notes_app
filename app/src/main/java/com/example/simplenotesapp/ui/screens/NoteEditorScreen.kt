package com.example.simplenotesapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteEditorScreen(
    initialTitle: String = "",
    initialContent: String = "",
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(initialTitle) }
    var content by remember { mutableStateOf(initialContent) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // takes remaining space
            maxLines = Int.MAX_VALUE // allow multi-line
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { onSave(title, content) }) {
                Text("Save")
            }
        }
    }
}