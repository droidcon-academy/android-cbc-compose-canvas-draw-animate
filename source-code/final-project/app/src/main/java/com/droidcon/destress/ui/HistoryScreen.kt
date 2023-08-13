package com.droidcon.destress.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HistoryScreen() {
    Text("History")
}

@Preview
@Composable
private fun HistoryPreview() {
    DeStressTheme {
        HistoryScreen()
    }
}