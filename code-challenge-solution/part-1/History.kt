// Coding challenge: Implement the code snipit to draw the bar graphs
//                   for when only the focus events are selected

// Solution:
showFocus -> {
    val focusHeight = size.height * (focusCount.toFloat() / maxEventsPerDay)
    drawRect(
        Pond2,
        topLeft = Offset(day * barWidth, size.height - focusHeight),
        size = Size(width = barWidth, height = focusHeight)
    )
}
