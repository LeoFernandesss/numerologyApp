package io.github.leofernandesss.numerologiaads.presenter.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import io.github.leofernandesss.numerologiaads.presenter.screens.home.NumerologyScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NumerologyScreen()
            }
        }
    }

}