package com.droidcon.destress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.droidcon.destress.ui.App
import com.droidcon.destress.ui.theme.DeStressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeStressTheme {
                App()
            }
        }
    }
}