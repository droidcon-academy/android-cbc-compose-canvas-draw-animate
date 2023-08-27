package com.droidcon.destress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.Pond1
import com.droidcon.destress.ui.theme.Start

@Composable
fun FocusBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    if (isRunning) {
        Box(modifier.background(Pond1))
    } else {
        Box(modifier.background(Start))
    }
}

@Preview
@Composable
fun PreviewFocusBox() {
    FocusBox(modifier = Modifier.fillMaxSize())
}