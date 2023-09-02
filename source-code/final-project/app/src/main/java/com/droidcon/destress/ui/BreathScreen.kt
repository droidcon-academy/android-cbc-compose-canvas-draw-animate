package com.droidcon.destress.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.BreathBox
import com.droidcon.destress.R
import com.droidcon.destress.ui.component.Timer
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun BreathScreen(durationSeconds: Int, activityComplete: (Int) -> Unit) {
    var isRunning by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BreathBox(
            isRunning = isRunning,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.breath_instructions)
        )
        Timer(
            durationSeconds = durationSeconds,
            onStart = { isRunning = true },
            onComplete = {
                isRunning = false
                activityComplete(durationSeconds)
            }
        )
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