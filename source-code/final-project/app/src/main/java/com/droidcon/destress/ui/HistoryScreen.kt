package com.droidcon.destress.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.History
import com.droidcon.destress.domain.RelaxEvent
import com.droidcon.destress.domain.testList
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HistoryScreen(events: List<RelaxEvent>) {
    History(events)
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
