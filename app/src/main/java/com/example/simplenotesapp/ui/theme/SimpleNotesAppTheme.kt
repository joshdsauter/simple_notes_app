package com.example.simplenotesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.simplenotesapp.ui.graphics.DarkColorScheme
import com.example.simplenotesapp.ui.graphics.LightColorScheme
import com.example.simplenotesapp.ui.graphics.NotesTypography
import com.example.simplenotesapp.ui.graphics.Shapes

@Composable
fun SimpleNotesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = NotesTypography,
        shapes = Shapes,
        content = content
    )
}
