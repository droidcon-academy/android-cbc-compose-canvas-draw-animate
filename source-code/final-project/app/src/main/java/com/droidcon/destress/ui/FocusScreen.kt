package com.droidcon.destress.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun FocusScreen(activityComplete:(Int)->Unit) {
    Button(onClick = { activityComplete(60) }) {
        Text("Focus")
    }
}

@Preview
@Composable
private fun FocusPreview() {
    DeStressTheme {
        FocusScreen {}
    }
}