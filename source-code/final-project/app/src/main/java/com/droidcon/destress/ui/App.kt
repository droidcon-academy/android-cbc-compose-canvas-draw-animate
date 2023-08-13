package com.droidcon.destress.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.DeStressTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var currentDestination by remember { mutableStateOf(Destination.Home) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "DeStress",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        bottomBar = {
            BottomBar {
                currentDestination = it
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (currentDestination) {
                Destination.Home -> HomeScreen()
                Destination.Breath -> BreathScreen()
                Destination.Focus -> FocusScreen()
                Destination.History -> HistoryScreen()
            }
        }
    }
}

@Composable
fun BottomBar(setDestination: (Destination) -> Unit) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { setDestination(Destination.Home) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(onClick = { setDestination(Destination.Breath) }) {
                Icon(Icons.Filled.Face, contentDescription = "Deep Breath Activity")
            }
            IconButton(onClick = { setDestination(Destination.Focus) }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Focus Activity",
                )
            }
            IconButton(onClick = { setDestination(Destination.History) }) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Activity History",
                )
            }
        },
    )
}

enum class Destination {
    Home, Breath, Focus, History
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeStressTheme {
        App()
    }
}