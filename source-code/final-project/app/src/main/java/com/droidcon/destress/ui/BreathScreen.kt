package com.droidcon.destress.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.R
import com.droidcon.destress.ui.component.Timer
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun BreathScreen(durationSeconds: Int, activityComplete: (Int) -> Unit) {

    var isRunning by remember { mutableStateOf(false) }
    Column {
        BreathBox(Modifier.fillMaxWidth().height(300.dp))
        Text("Take a deep breath, In... Out")
        Timer(isRunning, durationSeconds) {
            isRunning = false
            activityComplete(durationSeconds)
        }
        Button(
            onClick = { isRunning = true },
            enabled = !isRunning
        ) {
            Text(stringResource(R.string.start))
        }
    }
}

@Preview
@Composable
private fun BreathPreview() {
    DeStressTheme {
        Surface {
            BreathScreen(10) {}
        }
    }
}