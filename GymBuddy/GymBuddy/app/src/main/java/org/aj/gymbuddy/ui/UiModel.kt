package org.aj.gymbuddy.ui

import org.aj.gymbuddy.ui.screen.exercises.ExercisesModel
import org.aj.gymbuddy.ui.screen.settings.SettingsModel
import org.aj.gymbuddy.ui.screen.workouts.WorkoutsModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.aj.gymbuddy.ui.screen.workout.WorkoutModel

val uiModule = module {
    viewModel {
        WorkoutModel(get())
    }
    viewModel {
        WorkoutsModel(get())
    }
    viewModel {
        ExercisesModel()
    }
    viewModel {
        SettingsModel(get())
    }
}
