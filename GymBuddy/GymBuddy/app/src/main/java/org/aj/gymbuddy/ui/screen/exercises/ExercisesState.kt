package org.aj.gymbuddy.ui.screen.exercises

import org.aj.gymbuddy.db.ExerciseCategory

data class ExercisesState(
    val categories: List<ExerciseCategory> = ExerciseCategory.entries,
    val expanded: List<ExerciseCategory> = emptyList(),
    val filter: String = "",
    val selected: List<ExerciseCategory.Exercise> = emptyList(),
) {
    val exercises: List<ExerciseCategory.Exercise>
        get() = categories
            .flatMap(ExerciseCategory::exercises)
            .filter { it.title.lowercase().contains(filter.lowercase()) }
}
