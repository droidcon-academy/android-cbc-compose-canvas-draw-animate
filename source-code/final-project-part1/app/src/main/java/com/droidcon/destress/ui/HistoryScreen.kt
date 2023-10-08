package com.droidcon.destress.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.History
import com.droidcon.destress.domain.DayCount
import com.droidcon.destress.domain.testList
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HistoryScreen(events: List<DayCount>) {
    var showBreath by remember { mutableStateOf(true) }
    var showFocus by remember { mutableStateOf(true) }
    Column {
        val focusCount = events.last().focusCount
        val breathCount = events.last().breathCount
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Your history for the past ${events.size} days."
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "You completed $focusCount focus events and $breathCount breath events today."
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Your max daily focus events in ${events.size} days: "
        )
        Text(modifier = Modifier.padding(8.dp), text = "${events.maxOf { it.focusCount }}")
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Your max daily breath events in ${events.size} days: "
        )
        Text(modifier = Modifier.padding(8.dp), text = "${events.maxOf { it.breathCount }}")
        History(
            modifier = Modifier.weight(1f),
            events = events,
            showFocus = showFocus,
            showBreath = showBreath
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = showBreath,
                onCheckedChange = { showBreath = it }
            )
            Text("Deep Breath Activity")

        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = showFocus,
                onCheckedChange = { showFocus = it }
            )
            Text("Focus Activity")

        }

    }
}

@Preview
@Composable
private fun HistoryPreview() {
    DeStressTheme {
        Surface {
            HistoryScreen(testList)
        }
    }
}
