package com.example.simplenotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenotesapp.ui.navigation.NotesAppNavHost
import com.example.simplenotesapp.ui.screens.NotesHomeScreen
import com.example.simplenotesapp.ui.theme.SimpleNotesAppTheme
import com.example.simplenotesapp.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkMode = themeViewModel.isDarkMode.collectAsState().value

            SimpleNotesAppTheme(darkTheme = isDarkMode) {
                NotesAppNavHost(themeViewModel = themeViewModel)
            }
        }
    }
}