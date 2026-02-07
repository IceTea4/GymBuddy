package org.aj.gymbuddy.ui.screen.workout

import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.db.WorkoutEntity

data class WorkoutState(
    val workout: WorkoutEntity,
    val item: String = "",
    val nameError: Boolean = false,
    val itemError: Boolean = false,
    val selected: List<ExerciseCategory.Exercise> = emptyList(),
    val likes: List<WorkoutEntity> = emptyList(),
)
