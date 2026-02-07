package org.aj.gymbuddy.ui.screen.exercises

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.lang.then
import org.aj.gymbuddy.ui.compose.NavigationBottomBar
import org.aj.gymbuddy.ui.compose.SelectionButtons
import org.aj.gymbuddy.ui.compose.TopBar
import org.aj.gymbuddy.ui.screen.workout.WorkoutModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExercisesScreen(
    navController: NavHostController,
    model: ExercisesModel = koinViewModel(),
) {
    val state by model.state.collectAsStateWithLifecycle()
    var popupItem by remember { mutableStateOf<ExerciseCategory.Exercise?>(null) }
    val focusManager = LocalFocusManager.current
    val backStackEntry = remember { navController.getBackStackEntry("workouts") }
    val workoutModel: WorkoutModel = koinViewModel(owner = backStackEntry)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController,
                showExit = true,
                onExitClick = {
                    navController.navigate("workouts")
                },
            )
        },
        bottomBar = {
            NavigationBottomBar(navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth(),
                label = {
                    Text("Search exercises...")
                },
                value = state.filter,
                onValueChange = model::setFilter,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (state.filter.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Search",
                            modifier = Modifier
                                .clickable {
                                    model.setFilter("")
                                    focusManager.clearFocus()
                                }
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )

            Box(modifier = Modifier.fillMaxSize()) {

                AnimatedContent(
                    targetState = state.filter.isNotBlank()
                ) { filtered ->
                    if (filtered) {
                        ExercisesList(
                            exercises = state.exercises,
                            selected = state.selected,
                            onTap = {
                                if (state.selected.isNotEmpty()) {
                                    model.select(it)
                                } else {
                                    popupItem = it
                                }
                            },
                            onLongPress = {
                                if (state.selected.isEmpty()) {
                                    model.select(it)
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        CategoriesList(
                            categories = state.categories,
                            selected = state.selected,
                            expandedCategories = state.expanded,
                            onCategoryClick = { model.expand(it) },
                            onTap = {
                                if (state.selected.isNotEmpty()) {
                                    model.select(it)
                                } else {
                                    popupItem = it
                                }
                            },
                            onLongPress = {
                                if (state.selected.isEmpty()) {
                                    model.select(it)
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Column {
                    AnimatedVisibility(
                        visible = state.selected.isNotEmpty(),
                    ) {
                        SelectionButtons(
                            onCancel = model::clearSelection,
                            onAdd = {
                                val selectedExercises = state.selected
                                workoutModel.setSelected(selectedExercises)
                                navController.navigate(route = "workout")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = popupItem != null,
    ) {
        popupItem?.let {
            ExercisesPopup(exercise = it) {
                popupItem = null
            }
        }
    }

    LaunchedEffect(workoutModel) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.get<Boolean>("workout-saved")
            ?.then {
                model.clearSelection()
            }
    }
}
