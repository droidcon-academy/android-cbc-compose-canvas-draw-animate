package com.droidcon.destress.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepSlider(
    initialValue: Int,
    minValue: Int,
    maxValue: Int,
    label: String,
    onChange: (Int) -> Unit,
) {
    var value by remember { mutableStateOf(initialValue.toFloat()) }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row {
            Text(text = "$label: ", fontSize = 14.sp)
            Text(text = "%d".format(value.toInt()), fontSize = 14.sp)
        }
        Slider(
            value = value,
            valueRange = minValue.toFloat()..maxValue.toFloat(),
            steps = 19,
            onValueChange = { value = it; onChange(value.toInt()) },
            onValueChangeFinished = { onChange(value.toInt()) }
        )
    }
}