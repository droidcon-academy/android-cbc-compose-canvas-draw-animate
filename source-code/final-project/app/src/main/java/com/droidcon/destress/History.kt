package com.droidcon.destress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.domain.RelaxEvent
import com.droidcon.destress.domain.RelaxType
import com.droidcon.destress.domain.testList
import com.droidcon.destress.ui.theme.Grid
import com.droidcon.destress.ui.theme.Pond2
import com.droidcon.destress.ui.theme.Start
import com.droidcon.destress.ui.theme.Sun2
import kotlin.math.max

@Composable
fun History(
    modifier: Modifier = Modifier,
    events: List<RelaxEvent>,
    showFocus: Boolean = true,
    showBreath: Boolean = true
) {
    val focusCount = events.count { it.type == RelaxType.DeepFocus }
    val breathCount = events.size - focusCount
    val maxEventsPerDay = max(focusCount, breathCount)
    Canvas(
        modifier
            .padding(8.dp)
            .aspectRatio(3 / 2f)
    ) {
        val barWidthPx = 1.dp.toPx()
        drawRect(Grid, style = Stroke(barWidthPx))
        val days = 7 //ToDO get days from events
        val barWidth = size.width / days
        repeat(days) { day ->// this changes to a loop through the days
            when {
                (showBreath && showFocus && focusCount > 0 && breathCount > 0) -> {
                    val focusHeight = size.height * (focusCount.toFloat() / maxEventsPerDay)
                    val breathHeight = size.height * (breathCount.toFloat() / maxEventsPerDay)
                    drawRect(
                        Sun2,
                        topLeft = Offset(day * barWidth, size.height - breathHeight),
                        size = Size(width = barWidth / 2, height = breathHeight)
                    )
                    drawRect(
                        Pond2,
                        topLeft = Offset(day * barWidth + barWidth / 2, size.height - focusHeight),
                        size = Size(width = barWidth / 2, height = focusHeight)
                    )

                }

                showBreath && breathCount > 0 -> {
                    val breathHeight = size.height * (breathCount.toFloat() / maxEventsPerDay)
                    drawRect(
                        Sun2,
                        topLeft = Offset(day * barWidth, size.height - breathHeight),
                        size = Size(width = barWidth, height = breathHeight)
                    )
                }

                showFocus && focusCount > 0 -> {
                    val focusHeight = size.height * (focusCount.toFloat() / maxEventsPerDay)
                    drawRect(
                        Pond2,
                        topLeft = Offset(day * barWidth, size.height - focusHeight),
                        size = Size(width = barWidth , height = focusHeight)
                    )

                }
            }
        }

        val horizontalLines = 3
        val sectionSize = size.height / (horizontalLines + 1)
        repeat(horizontalLines) { i ->
            val startY = sectionSize * (i + 1)
            drawLine(
                Grid,
                start = Offset(0F, startY),
                end = Offset(size.width, startY),
                strokeWidth = barWidthPx
            )
        }
    }
}

@Preview
@Composable
fun PreviewHistory() {
    History(events = testList)
}
