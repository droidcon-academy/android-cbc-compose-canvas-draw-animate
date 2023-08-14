package com.droidcon.destress.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.droidcon.destress.R
import kotlinx.coroutines.delay

@Composable
fun Timer(isRunning:Boolean, durationSeconds:Int, complete: (Int) -> Unit) {
    var currentValue by remember { mutableStateOf(durationSeconds) }
    LaunchedEffect(key1 = isRunning, key2 = currentValue) {
        if (currentValue > 0 && isRunning) {
            delay(1000L)
            currentValue -= 1
            if (currentValue <= 0) {
                complete(durationSeconds)
            }
        }
    }

    if (isRunning) {
        Text("$currentValue")

    } else {
        Text("Well Done!")
    }

}