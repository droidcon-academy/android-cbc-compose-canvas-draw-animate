package com.droidcon.destress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.domain.DayCount
import com.droidcon.destress.domain.testList
import com.droidcon.destress.ui.theme.Grid
import com.droidcon.destress.ui.theme.Pond2
import com.droidcon.destress.ui.theme.Sun2

@Composable
fun History(
    modifier: Modifier = Modifier,
    events: List<DayCount>,
    showFocus: Boolean = true,
    showBreath: Boolean = true
) {
    val maxEventsPerDay = events.flatMap { listOf(it.focusCount, it.breathCount) }.max()
    Canvas(
        modifier
            .padding(8.dp)
            .aspectRatio(3 / 2f)
    ) {
        val days = events.size
        val barWidth = size.width / days
        graphPaper(days)
        if (maxEventsPerDay > 0) {
            repeat(days) { day ->// this changes to a loop through the days
                val focusCount = events[day].focusCount
                val breathCount = events[day].breathCount
                when {
                    (showBreath && showFocus) -> {
                        val focusHeight = size.height * (focusCount.toFloat() / maxEventsPerDay)
                        val breathHeight = size.height * (breathCount.toFloat() / maxEventsPerDay)
                        drawRect(
                            Sun2,
                            topLeft = Offset(day * barWidth, size.height - breathHeight),
                            size = Size(width = barWidth / 2, height = breathHeight)
                        )
                        drawRect(
                            Pond2,
                            topLeft = Offset(
                                day * barWidth + barWidth / 2,
                                size.height - focusHeight
                            ),
                            size = Size(width = barWidth / 2, height = focusHeight)
                        )

                    }

                    showBreath -> {
                        val breathHeight = size.height * (breathCount.toFloat() / maxEventsPerDay)
                        drawRect(
                            Sun2,
                            topLeft = Offset(day * barWidth, size.height - breathHeight),
                            size = Size(width = barWidth, height = breathHeight)
                        )
                    }

                    showFocus -> {
                        val focusHeight = size.height * (focusCount.toFloat() / maxEventsPerDay)
                        drawRect(
                            Pond2,
                            topLeft = Offset(day * barWidth, size.height - focusHeight),
                            size = Size(width = barWidth, height = focusHeight)
                        )
                    }
                }
            }
        }
    }
}

fun DrawScope.graphPaper(days: Int) {
    val barWidthPx = 1.dp.toPx()
    drawRect(Grid, style = Stroke(barWidthPx))
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
    val verticalSize = size.width / days
    repeat(days) { i ->
        val startX = verticalSize * (i + 1)
        drawLine(
            Grid,
            start = Offset(startX, 0F),
            end = Offset(startX, size.height),
            strokeWidth = barWidthPx
        )
    }

}

@Preview
@Composable
fun PreviewHistory() {
    History(events = testList)
}

@Preview
@Composable
fun PreviewHistoryBreath() {
    History(events = testList, showBreath = true, showFocus = false)
}

@Preview
@Composable
fun PreviewHistoryFocus() {
    History(events = testList, showBreath = false, showFocus = true)
}
