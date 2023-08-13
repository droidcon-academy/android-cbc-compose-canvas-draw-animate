package com.droidcon.destress.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.droidcon.destress.R
import kotlinx.coroutines.delay

@Composable
fun Timer(complete: (Int) -> Unit) {
    var durationSeconds by remember { mutableStateOf(30) }
    var isRunning by remember { mutableStateOf(false) }
    var currentValue by remember { mutableStateOf(durationSeconds) }
    LaunchedEffect(key1 = isRunning, key2 = currentValue) {
        if (currentValue > 0 && isRunning) {
            delay(1000L)
            currentValue -= 1
            if (currentValue <= 0) {
                isRunning = false
                complete(durationSeconds)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isRunning) {
            Text("Seconds left $currentValue")

        } else {
            StepSlider(
                initialValue = 30,
                minValue = 10,
                maxValue = 600,
                label = stringResource(R.string.seconds),
                onChange = { durationSeconds = it }
            )
        }
        Button(
            onClick = {
                isRunning = true
                currentValue = durationSeconds
            },
            enabled = !isRunning
        ) {
            Text(stringResource(R.string.start))
        }
    }
}