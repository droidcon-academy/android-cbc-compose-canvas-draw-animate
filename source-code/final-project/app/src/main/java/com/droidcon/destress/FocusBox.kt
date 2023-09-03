package com.droidcon.destress

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.droidcon.destress.ui.theme.Lilly1
import com.droidcon.destress.ui.theme.Lilly2
import com.droidcon.destress.ui.theme.LillyCore1
import com.droidcon.destress.ui.theme.LillyCore2
import com.droidcon.destress.ui.theme.LillyPad2
import com.droidcon.destress.ui.theme.Pond3
import com.droidcon.destress.ui.theme.Ripple1
import com.droidcon.destress.ui.theme.Start
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
fun FocusBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    if (isRunning) {
        val sizedLillyCache = remember(petals) {
            mutableMapOf<Size, RoundedPolygon>()
        }
        val sizedLillyCrownCache = remember(crown) {
            mutableMapOf<Size, RoundedPolygon>()
        }
        val lillyTransition = rememberInfiniteTransition(label = "focus transition")
        val focusRotate by lillyTransition.animateFloat(
            initialValue = 0f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(4_000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "Lilly rotate "
        )

        var rippleOffset by remember { mutableStateOf(Offset.Zero) }
        val rippleRadius = remember { Animatable(1f) }
        val scope = rememberCoroutineScope()

        Box(
            modifier
                .fillMaxSize()
                .background(Pond3)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        // remember the offset and start the ripple
                        rippleOffset = offset
                        scope.launch {
                            rippleRadius.snapTo(50f)
                            rippleRadius.animateTo(10000f, animationSpec = tween(10000))
                        }
                    }
                }
                .drawBehind {
                    drawCircle(
                        color = Ripple1,
                        center = rippleOffset,
                        radius = rippleRadius.value,
                        style = Stroke(width = 1.dp.toPx())
                    )
                    val sizedLilly = sizedLillyCache.getOrPut(size) {
                        val matrix = calculateMatrix(width = size.width, height = size.height)
                        RoundedPolygon(petals).apply { transform(matrix) }
                    }
                    val sizedCrown = sizedLillyCrownCache.getOrPut(size) {
                        val matrix = calculateMatrix(width = size.width, height = size.height)
                        RoundedPolygon(crown).apply { transform(matrix) }
                    }
                    rotate(focusRotate) {
                        lillyPad()
                        translate(left = -70f, top = -70f) {
                            scale(scaleX = 0.75f, scaleY = 0.75f) {
                                drawPath(
                                    path = sizedLilly
                                        .toPath()
                                        .asComposePath(),
                                    brush = lillyBrush
                                )
                            }
                            scale(scaleX = 0.2f, scaleY = 0.2f) {
                                drawPath(
                                    path = sizedCrown
                                        .toPath()
                                        .asComposePath(),
                                    brush = lillyCoreBrush
                                )
                            }
                        }
                    }
                })

    } else {
        Box(
            modifier
                .background(Start)
                .drawBehind {
                    lillyPad(Ripple1, Stroke(width = 2f))
                })
    }
}

val petals = RoundedPolygon.star(
    numVerticesPerRadius = 12,
    innerRadius = 0.5f
)

val crown = RoundedPolygon.star(
    numVerticesPerRadius = 16,
    innerRadius = .8f,
    innerRounding = CornerRounding(radius = 0.1f)
)

fun DrawScope.lillyPad(color: Color = LillyPad2, style: DrawStyle = Fill) {
    val diameter = size.width * 0.6f
    translate(
        left = center.x - diameter / 2,
        top = center.y - diameter / 2
    ) {
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 330f,
            useCenter = true,
            style = style,
            size = Size(diameter, diameter)
        )
    }
}

val lillyBrush = Brush.radialGradient(listOf(Lilly2, Lilly1))
val lillyCoreBrush = Brush.radialGradient(listOf(LillyCore2, LillyCore1))


//by default the library creates canonical shapes with a radius of 1 around a center at (0, 0)
//https://medium.com/androiddevelopers/the-shape-of-things-to-come-1c7663d9dbc0
private fun calculateMatrix(
    bounds: RectF = RectF(-1f, -1f, 1f, 1f),
    width: Float,
    height: Float
): Matrix {
    val originalWidth = bounds.right - bounds.left
    val originalHeight = bounds.bottom - bounds.top
    val scale = min(width / originalWidth, height / originalHeight)
    val newLeft = bounds.left - (width / scale - originalWidth) / 2
    val newTop = bounds.top - (height / scale - originalHeight) / 2
    val matrix = Matrix()
    matrix.setTranslate(-newLeft, -newTop)
    matrix.postScale(scale, scale)
    return matrix
}

@Preview
@Composable
fun PreviewFocusBox() {
    FocusBox(modifier = Modifier.fillMaxSize())
}

@Preview
@Composable
fun PreviewFocusBoxPause() {
    FocusBox(modifier = Modifier.fillMaxSize(), isRunning = false)
}
