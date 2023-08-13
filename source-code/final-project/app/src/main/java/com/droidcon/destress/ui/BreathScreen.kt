package com.droidcon.destress.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun BreathScreen(activityComplete: (Int) -> Unit) {
    Button(onClick = { activityComplete(60) }) {
        Text("Breath")
    }
}

@Preview
@Composable
private fun BreathPreview() {
    DeStressTheme {
        BreathScreen {}
    }
}