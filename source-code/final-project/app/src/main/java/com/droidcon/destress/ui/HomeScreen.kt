package com.droidcon.destress.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.destress.R
import com.droidcon.destress.ui.component.StepSlider
import com.droidcon.destress.ui.theme.DeStressTheme
import com.droidcon.destress.ui.theme.Pond2
import com.droidcon.destress.ui.theme.Sun2

@Composable
fun HomeScreen(setDuration: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.welcome_body),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.size(16.dp))
        Row {
            Icon(
                Icons.Filled.Face,
                contentDescription = stringResource(R.string.deep_breath_activity),
                tint = Sun2
            )
            Text("Deep Breath")
        }

        Row {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = stringResource(R.string.focus_activity),
                tint = Pond2
            )
            Text("Focus")
        }

        Spacer(Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.time_label),
            style = MaterialTheme.typography.headlineMedium
        )

        StepSlider(
            initialValue = 10,
            minValue = 10,
            maxValue = 600,
            label = stringResource(R.string.seconds),
            onChange = { setDuration(it) }
        )

        Spacer(Modifier.size(16.dp))

        Row {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = stringResource(R.string.activity_history),
            )
            Text(stringResource(R.string.history_label))
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    DeStressTheme {
        Surface {
            HomeScreen {}
        }
    }
}