package org.aj.gymbuddy.ui.compose

import java.time.LocalDate

data class DayCardData(
    val date: LocalDate,
    val events: List<EventData> = emptyList()
)

data class EventData(
    val id: String,
    val title: String,
    val description: String,
    val time: String
)