package org.aj.gymbuddy

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.aj.gymbuddy.ui.screen.exercises.ExercisesScreen
import org.aj.gymbuddy.ui.screen.workout.WorkoutScreen
import org.aj.gymbuddy.ui.screen.home.EventScreen
import org.aj.gymbuddy.ui.screen.workouts.WorkoutsScreen
import org.aj.gymbuddy.ui.screen.home.HomeScreen
import org.aj.gymbuddy.ui.screen.home.NextWeekScreen
import org.aj.gymbuddy.ui.screen.home.PreviousWeekScreen
import org.aj.gymbuddy.ui.screen.settings.SettingsScreen
import org.aj.gymbuddy.ui.screen.workoutsedit.WorkoutsEditScreen
import org.aj.gymbuddy.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import org.aj.gymbuddy.ui.screen.settings.SettingsModel
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            val settingsModel: SettingsModel = koinViewModel()
            val isDarkTheme by settingsModel.isDarkThemeEnabledFlow.collectAsState()


            AppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {

                    composable(route = "workouts") {
                        WorkoutsScreen(navController)
                    }
                    composable(route = "home") {
                        HomeScreen(navController)
                    }
                    composable(route = "settings") {
                        SettingsScreen(navController)
                    }
                    composable(route = "workout") {
                        WorkoutScreen(navController)
                    }
                    composable(route = "event") {
                        EventScreen(navController)
                    }
                    composable(route = "previous") {
                        PreviousWeekScreen(navController)
                    }
                    composable(route = "next") {
                        NextWeekScreen(navController)
                    }
                    composable(route = "exercises") {
                        ExercisesScreen(navController)
                    }
                    composable(route = "edit_workout/{workoutId}") { backStackEntry ->
                        val workoutId = backStackEntry.arguments?.getString("workoutId")
                        WorkoutsEditScreen(
                            workoutId = workoutId ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}


