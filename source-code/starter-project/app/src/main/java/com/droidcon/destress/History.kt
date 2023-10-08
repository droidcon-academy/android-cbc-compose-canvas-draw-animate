package com.droidcon.destress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
    Spacer(modifier.fillMaxSize().background(Color.Black))
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
