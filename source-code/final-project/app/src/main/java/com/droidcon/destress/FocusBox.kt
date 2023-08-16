package com.droidcon.destress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FocusBox(isRunning: Boolean = true, modifier: Modifier = Modifier) {
    if (isRunning)
        Box(modifier.background(Color.Green))
    else
        Box(modifier.background(Color.White))
}

@Preview
@Composable
fun PreviewFocusBox() {
    FocusBox(modifier = Modifier.fillMaxSize())
}