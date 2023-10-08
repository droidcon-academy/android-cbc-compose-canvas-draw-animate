package com.droidcon.destress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BreathBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    if (isRunning) {
        Box(
            modifier
                .fillMaxSize()
                .background(Color.Blue))
    } else {
        Box(
            modifier
                .fillMaxSize()
                .background(Color.DarkGray))
    }
}

@Preview
@Composable
fun PreviewBreathBox() {
    BreathBox(modifier = Modifier.fillMaxSize(), isRunning = true)
}

@Preview
@Composable
fun PreviewBreathBoxPause() {
    BreathBox(modifier = Modifier.fillMaxSize(), isRunning = false)
}