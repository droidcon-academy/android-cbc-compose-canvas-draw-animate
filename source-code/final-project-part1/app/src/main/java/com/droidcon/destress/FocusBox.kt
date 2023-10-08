package com.droidcon.destress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FocusBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    if (isRunning) {
        Box(modifier.fillMaxSize().background(Color.Green))
    } else {
        Box(modifier.fillMaxSize().background(Color.DarkGray))
    }
}

@Preview
@Composable
fun PreviewFocusBox() {
    FocusBox(modifier = Modifier.fillMaxSize(), isRunning = true)
}

@Preview
@Composable
fun PreviewFocusBoxPause() {
    FocusBox(modifier = Modifier.fillMaxSize(), isRunning = false)
}