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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.droidcon.destress.domain.HistoryViewModel
import com.droidcon.destress.ui.theme.DeStressTheme
import com.droidcon.destress.ui.theme.Pond2
import com.droidcon.destress.ui.theme.Sun2


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var currentDestination by rememberSaveable { mutableStateOf(Destination.Home) }
    val viewModel: HistoryViewModel = viewModel()
    val sevenDayEventCount by viewModel.sevenDayEventCount.collectAsStateWithLifecycle()
    val duration by viewModel.duration.collectAsStateWithLifecycle()

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
                Destination.Home -> HomeScreen(viewModel::setDuration)
                Destination.Breath -> BreathScreen(duration, viewModel::breathComplete)
                Destination.Focus -> FocusScreen(duration, viewModel::focusComplete)
                Destination.History -> HistoryScreen(sevenDayEventCount)
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
                Icon(Icons.Filled.Face, contentDescription = "Deep Breath Activity", tint = Sun2)
            }
            IconButton(onClick = { setDestination(Destination.Focus) }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Focus Activity",
                    tint = Pond2
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