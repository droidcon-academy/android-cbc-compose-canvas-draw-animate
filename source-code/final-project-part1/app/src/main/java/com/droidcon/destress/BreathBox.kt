package com.droidcon.destress

import androidx.compose.animation.Crossfade
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.ui.theme.Cloud
import com.droidcon.destress.ui.theme.Ripple1
import com.droidcon.destress.ui.theme.Sky
import com.droidcon.destress.ui.theme.Start
import com.droidcon.destress.ui.theme.Sun1
import com.droidcon.destress.ui.theme.Sun2


val skyBrush = Brush.radialGradient(listOf(Cloud, Sky))
val sunBrush = Brush.radialGradient(listOf(Sun2, Sun1))

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
    Crossfade(
        targetState = isRunning,
        label = "BreathBox crossfade",
        animationSpec = tween(1_000, easing = FastOutSlowInEasing)
    ) {
        if (it) {
            Canvas(
                modifier
                    .fillMaxSize()
                    .background(skyBrush)
            ) {
                scale(scaleX = breathPulse, scaleY = breathPulse) {
                    drawCircle(brush = sunBrush, radius = size.width / 5, center = center)
                }
            }
        } else {
            Box(
                modifier
                    .fillMaxSize()
                    .background(Start)
                    .drawBehind {
                        drawCircle(Ripple1, size.width / 5, center = center, style = Stroke(2.0f))
                    })
        }
    }
}

@Preview
@Composable
fun PreviewBreathBox() {
    BreathBox(modifier = Modifier.fillMaxSize())
}

@Preview
@Composable
fun PreviewBreathBoxPause() {
    BreathBox(modifier = Modifier.fillMaxSize(), isRunning = false)
}