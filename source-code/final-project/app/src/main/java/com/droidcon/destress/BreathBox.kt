package com.droidcon.destress

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.Sky
import com.droidcon.destress.ui.theme.Start
import com.droidcon.destress.ui.theme.Sun

@Composable
fun BreathBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    val infiniteTransition = rememberInfiniteTransition(label = "breath transition")
    val breathPulse by infiniteTransition.animateFloat(
        initialValue = 0.75f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(4_000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "sun size"
    )
    if (isRunning) {
        Canvas(modifier.background(Sky)) {
            withTransform({
                scale(scaleX = breathPulse, scaleY = breathPulse)
            }) {
                drawCircle(color = Sun, radius = size.width / 5, center = center)
            }
        }
    } else {
        Box(modifier.background(Start))
    }
}

@Preview
@Composable
fun PreviewBreathBox() {
    BreathBox(modifier = Modifier.fillMaxSize())
}