package com.droidcon.destress.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.domain.RelaxEvent
import com.droidcon.destress.domain.RelaxType
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HistoryScreen(events: List<RelaxEvent>) {
    val focusCount = events.count { it.type == RelaxType.DeepFocus }
    val breathCount = events.size - focusCount
    Text("You completed $focusCount focus events and $breathCount breath events")
}

@Preview
@Composable
private fun HistoryPreview() {
    DeStressTheme {
        Surface {
            HistoryScreen(emptyList())
        }
    }
}