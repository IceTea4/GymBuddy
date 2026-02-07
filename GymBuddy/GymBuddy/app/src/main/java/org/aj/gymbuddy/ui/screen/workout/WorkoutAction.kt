package org.aj.gymbuddy.ui.screen.workout

sealed class WorkoutAction {
    object Back: WorkoutAction()
    object Edit: WorkoutAction()
}
