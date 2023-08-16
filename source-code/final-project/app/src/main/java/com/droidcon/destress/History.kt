package com.droidcon.destress

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.domain.RelaxEvent
import com.droidcon.destress.domain.RelaxType
import com.droidcon.destress.domain.testList

@Composable
fun History(events: List<RelaxEvent>) {
    val focusCount = events.count { it.type == RelaxType.DeepFocus }
    val breathCount = events.size - focusCount
    Text("You completed $focusCount focus events and $breathCount breath events")
}

@Preview
@Composable
fun PreviewHistory() {
    History(testList)
}
