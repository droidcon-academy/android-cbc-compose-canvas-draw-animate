// Canvas setup
Canvas(
    modifier
        .padding(8.dp)
        .aspectRatio(3 / 2f)
) {
    // graph paper and bars go here

}

// Some calculations
    val maxEventsPerDay = events.flatMap { listOf(it.focusCount, it.breathCount) }.max()
    val days = events.size
    val barWidth = size.width / days

// graph paper Outline
fun DrawScope.graphPaper(days: Int) {
    val linedWidthPx = 1.dp.toPx()
    drawRect(Grid, style = Stroke(linedWidthPx))
}

// graph paper horizontal lines
    val horizontalLines = 3
    val sectionSize = size.height / (horizontalLines + 1)
    repeat(horizontalLines) { i ->
        val startY = sectionSize * (i + 1)
        drawLine(
            Grid,
            start = Offset(0F, startY),
            end = Offset(size.width, startY),
            strokeWidth = linedWidthPx
        )
    }

// graph paper vertical lines
    val verticalSize = size.width / days
    repeat(days) { i ->
        val startX = verticalSize * (i + 1)
        drawLine(
            Grid,
            start = Offset(startX, 0F),
            end = Offset(startX, size.height),
            strokeWidth = linedWidthPx
        )
    }

// Bar graph calculations

        if (maxEventsPerDay > 0) {
            repeat(days) { day ->// this changes to a loop through the days
                val focusCount = events[day].focusCount
                val breathCount = events[day].breathCount
                when {
                    (showBreath && showFocus) -> TODO(reason = "Bar graph for both implementation goes here")
                    showBreath -> TODO(reason = "Bar graph for breath implementation goes here")
                    showFocus -> TODO(reason = "Coding challenge: Bar graph for focus implementation goes here")
                }
            }
        }

// Bar graph for both event types
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
    showBreath -> TODO(reason = "Bar graph for breath implementation goes here")
    showFocus -> TODO(reason = "Coding challenge: Bar graph for focus implementation goes here")
}

// Draw bar graph for breath events only
    showBreath -> {
        val breathHeight = size.height * (breathCount.toFloat() / maxEventsPerDay)
        drawRect(
            Sun2,
            topLeft = Offset(day * barWidth, size.height - breathHeight),
            size = Size(width = barWidth, height = breathHeight)
        )
    }
