package com.droidcon.destress.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HomeScreen() {
    Text("Home")
}

@Preview
@Composable
private fun HomePreview() {
    DeStressTheme {
        HomeScreen()
    }
}