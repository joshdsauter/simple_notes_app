package com.example.simplenotesapp.ui.graphics

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Define your custom colors
val Blue80 = Color(0xFF3366FF)
val Blue40 = Color(0xFF0033CC)
val Orange80 = Color(0xFFFF6D00)
val Orange40 = Color(0xFFCC5500)

// Light color scheme
val LightColorScheme = lightColorScheme(
    primary = Blue80,
    secondary = Orange80,
    background = Color(0xFFF0F0F0),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Dark color scheme
val DarkColorScheme = darkColorScheme(
    primary = Blue40,
    secondary = Orange40,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)
