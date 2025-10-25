package com.example.technicaltestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.technicaltestapp.ui.screen.PlayScreen
import com.example.technicaltestapp.ui.theme.TechnicalTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechnicalTestAppTheme {
                    PlayScreen()
            }
        }
    }
}
