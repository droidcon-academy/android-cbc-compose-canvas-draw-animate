package com.droidcon.destress.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

class HistoryViewModel : ViewModel() {

    private val _duration: MutableStateFlow<Int> = MutableStateFlow(10)
    val duration: StateFlow<Int> = _duration

    private val _history: MutableStateFlow<List<RelaxEvent>> = MutableStateFlow(emptyList())
    //val history: StateFlow<List<RelaxEvent>> = _history

    private val _sevenDayEventCount: MutableStateFlow<List<DayCount>> = MutableStateFlow(testList)
    val sevenDayEventCount: StateFlow<List<DayCount>> = _sevenDayEventCount

    fun breathComplete(durationSeconds: Int) {
        addEvent(durationSeconds, RelaxType.DeepBreath)
    }

    fun focusComplete(durationSeconds: Int) {
        addEvent(durationSeconds, RelaxType.DeepFocus)
    }

    fun setDuration(duration: Int) {
        _duration.value = duration
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
        val (breathCount, focusCount) = sevenDayEventCount.value.last()
        val newDayCount = when (type) {
            RelaxType.DeepFocus -> DayCount(focusCount = focusCount + 1, breathCount = breathCount)
            RelaxType.DeepBreath -> DayCount(focusCount = focusCount, breathCount = breathCount + 1)
        }
        _sevenDayEventCount.value = sevenDayEventCount.value.dropLast(1) + newDayCount
    }
}

data class DayCount(val breathCount: Int, val focusCount: Int)

data class RelaxEvent(val type: RelaxType, val start: Instant, val duration: Int)

enum class RelaxType {
    DeepFocus, DeepBreath
}

val testList = listOf(
    DayCount(2, 6),
    DayCount(0, 1),
    DayCount(8, 4),
    DayCount(6, 3),
    DayCount(4, 2),
    DayCount(1, 8),
    DayCount(4, 7),
    DayCount(4, 2),
    DayCount(8, 5),
    DayCount(6, 9),
    DayCount(1, 10),
    DayCount(7, 3),
    DayCount(3, 7),
    DayCount(0, 0),
)