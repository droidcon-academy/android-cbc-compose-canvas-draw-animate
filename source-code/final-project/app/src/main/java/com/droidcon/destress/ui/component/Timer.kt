package com.droidcon.destress.ui.component

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.R
import kotlinx.coroutines.delay

@Composable
fun Timer(
    modifier:Modifier = Modifier,
    durationSeconds: Int = 10,
    onValueChange: (Int) -> Unit = {},
    onStart: () -> Unit = {},
    onComplete: (Int) -> Unit = {}
) {
    var isRunning by rememberSaveable { mutableStateOf(false) }
    var currentValue by rememberSaveable { mutableStateOf(durationSeconds) }
    LaunchedEffect(key1 = isRunning, key2 = currentValue) {
        if (currentValue > 0 && isRunning) {
            delay(1000L)
            currentValue -= 1
            onValueChange(currentValue)
            if (currentValue <= 0) {
                isRunning = false
                currentValue = durationSeconds
                onComplete(durationSeconds)
            }
        }
    }

    Button(
        onClick = { isRunning = true; onStart() },
        modifier = modifier.widthIn(80.dp),
        enabled = !isRunning
    ) {
        if (isRunning)
            Text(currentValue.toString())
        else
            Text(stringResource(R.string.start))
    }
}

@Preview
@Composable
fun TimerPreview() {
    Timer()
}