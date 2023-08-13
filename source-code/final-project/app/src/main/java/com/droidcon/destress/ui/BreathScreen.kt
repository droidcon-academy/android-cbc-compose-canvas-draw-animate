package com.droidcon.destress.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.component.Timer
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun BreathScreen(activityComplete: (Int) -> Unit) {
    Timer(activityComplete)
}

@Preview
@Composable
private fun BreathPreview() {
    DeStressTheme {
        BreathScreen {}
    }
}