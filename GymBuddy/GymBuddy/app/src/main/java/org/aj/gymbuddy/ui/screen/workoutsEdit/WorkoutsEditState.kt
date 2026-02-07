package org.aj.gymbuddy.ui.screen.workoutsedit

import org.aj.gymbuddy.db.WorkoutEntity

data class WorkoutsEditState(
    val workout: WorkoutEntity? = null,
    val isLoading: Boolean = true,
    val isEditing: Boolean = false,
    val itemsMarkedForDeletion: Set<String> = emptySet()
)
