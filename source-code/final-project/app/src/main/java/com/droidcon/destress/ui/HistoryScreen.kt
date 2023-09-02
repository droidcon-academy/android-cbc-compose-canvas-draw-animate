package com.droidcon.destress.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.droidcon.destress.domain.RelaxEvent
import com.droidcon.destress.domain.RelaxType
import com.droidcon.destress.domain.testList
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HistoryScreen(events: List<RelaxEvent>) {
    var showBreath by remember { mutableStateOf(true) }
    var showFocus by remember { mutableStateOf(true) }
    Column {
        val focusCount = events.count { it.type == RelaxType.DeepFocus }
        val breathCount = events.size - focusCount
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
        Text(modifier = Modifier.padding(8.dp), text = "You completed $focusCount focus events and $breathCount breath events today.")
        History(
            modifier = Modifier.weight(1f),
            events = events,
            showFocus = showFocus,
            showBreath = showBreath
        )

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
