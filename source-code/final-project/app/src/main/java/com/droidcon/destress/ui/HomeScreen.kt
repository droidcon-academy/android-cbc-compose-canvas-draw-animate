package com.droidcon.destress.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.destress.R
import com.droidcon.destress.ui.theme.DeStressTheme

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = """
              There are two de-stress activities: 
              Deep Breath and Focus
              Choose the one and enjoy
              See the history of your activities
            """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    DeStressTheme {
        HomeScreen()
    }
}