package com.droidcon.destress

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.droidcon.destress.ui.theme.Lilly1
import com.droidcon.destress.ui.theme.Lilly2
import com.droidcon.destress.ui.theme.LillyCore1
import com.droidcon.destress.ui.theme.LillyPad1
import com.droidcon.destress.ui.theme.LillyPad2
import com.droidcon.destress.ui.theme.Pond1
import com.droidcon.destress.ui.theme.Start
import com.droidcon.destress.ui.theme.Sun1
import kotlin.math.max
import kotlin.math.min

@Composable
fun FocusBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    if (isRunning) {
        Box(modifier) {
            Canvas(Modifier.fillMaxSize().background(Pond1)) {
                lillyPad()
                lilly()
                //drawPath(path = petals.toPath().asComposePath(), color = Lilly1, style = Fill)
            }
            PolygonComposableImpl(shape = petals, Lilly2)
            PolygonComposableImpl(
                shape = crown,
                color = LillyCore1,
                modifier = Modifier.scale(0.2f)
            )
        }
    } else {
        Box(modifier.background(Start))
    }
}

@Preview
@Composable
fun PreviewFocusBox() {
    FocusBox(modifier = Modifier.fillMaxSize())
}

val petals = RoundedPolygon.star(
    numVerticesPerRadius = 16, innerRadius = .5f
)

val crown = RoundedPolygon.star(
    numVerticesPerRadius = 16, innerRadius = .8f
)

fun DrawScope.lillyPad() {
    translate(
        left = center.x - size.width / 4,
        top = center.y - size.height / 4
    ) {
        drawArc(
            color = LillyPad2,
            startAngle = 0f,
            sweepAngle = 330f,
            useCenter = true,
            style = Fill,
            size = Size(size.width / 2, size.width / 2)
        )
    }
}

fun DrawScope.lilly() {

    drawPath(path = petals.toPath().asComposePath(), color = Lilly1, style = Fill)
    drawCircle(color = Sun1, radius = 10f)

}

@Composable
internal fun PolygonComposableImpl(
    shape: RoundedPolygon,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    val sizedPolygonCache = remember(shape) {
        mutableMapOf<Size, RoundedPolygon>()
    }
    Box(
        modifier
            .fillMaxSize()
            .drawWithContent {
                drawContent()
                val sizedPolygon = sizedPolygonCache.getOrPut(size) {
                    val matrix = calculateMatrix(TheBounds, size.width/2, size.height)
                    RoundedPolygon(shape).apply { transform(matrix) }
                }
                drawPath(
                    sizedPolygon
                        .toPath()
                        .asComposePath(), color
                )
            }
    )
}

private val TheBounds = RectF(0f, 0f, 1f, 1f)

internal fun calculateMatrix(bounds: RectF, width: Float, height: Float): Matrix {
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

