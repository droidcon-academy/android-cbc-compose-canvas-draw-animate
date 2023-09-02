package com.droidcon.destress

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.droidcon.destress.ui.theme.Lilly1
import com.droidcon.destress.ui.theme.Lilly2
import com.droidcon.destress.ui.theme.LillyCore1
import com.droidcon.destress.ui.theme.LillyCore2
import com.droidcon.destress.ui.theme.LillyPad2
import com.droidcon.destress.ui.theme.Pond1
import com.droidcon.destress.ui.theme.Ripple1
import com.droidcon.destress.ui.theme.Start
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
        Box(
            modifier
                .fillMaxSize()
                .background(Pond1)
                .drawBehind {
                    lillyPad()
                    val sizedLilly = sizedLillyCache.getOrPut(size) {
                        val matrix = calculateMatrix(width = size.width, height = size.height)
                        RoundedPolygon(petals).apply { transform(matrix) }
                    }
                    val sizedCrown = sizedLillyCrownCache.getOrPut(size) {
                        val matrix = calculateMatrix(width = size.width, height = size.height)
                        RoundedPolygon(crown).apply { transform(matrix) }
                    }
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
