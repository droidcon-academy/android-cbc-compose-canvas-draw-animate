package com.droidcon.destress.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

class HistoryViewModel : ViewModel() {

    private val _history: MutableStateFlow<List<RelaxEvent>> = MutableStateFlow(emptyList())
    val history: StateFlow<List<RelaxEvent>> = _history

    fun breathComplete(durationSeconds: Int) {
        addEvent(durationSeconds, RelaxType.DeepBreath)
    }

    fun focusComplete(durationSeconds: Int) {
        addEvent(durationSeconds, RelaxType.DeepFocus)
    }

    private fun addEvent(durationSeconds: Int, type: RelaxType) {
        val now = Clock.System.now()
        val start = now.minus(durationSeconds.seconds)
        val newEvent = RelaxEvent(
            type = type,
            start = start,
            duration = durationSeconds
        )
        _history.value = _history.value + newEvent
    }
}

data class RelaxEvent(val type: RelaxType, val start: Instant, val duration: Int)

enum class RelaxType {
    DeepFocus, DeepBreath
}