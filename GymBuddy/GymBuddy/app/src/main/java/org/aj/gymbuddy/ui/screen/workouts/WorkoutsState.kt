package org.aj.gymbuddy.ui.screen.workouts

import org.aj.gymbuddy.db.WorkoutEntity

data class WorkoutsState(
    val workouts: List<WorkoutEntity> = emptyList(),
)
