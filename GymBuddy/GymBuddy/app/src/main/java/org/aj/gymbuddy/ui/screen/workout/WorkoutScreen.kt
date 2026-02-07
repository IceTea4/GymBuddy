package org.aj.gymbuddy.ui.screen.workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FabPosition
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import org.aj.gymbuddy.lang.then
import org.aj.gymbuddy.lang.otherwise
import org.aj.gymbuddy.ui.compose.FloatingSave
import org.aj.gymbuddy.ui.compose.ItemsList
import org.aj.gymbuddy.ui.compose.TopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WorkoutScreen(
    navController: NavHostController,
) {
    val backStackEntry = remember { navController.getBackStackEntry("workouts") }
    val model: WorkoutModel = koinViewModel(owner = backStackEntry)
    val state by model.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val focusRequester = remember { FocusRequester() }
    val imeVisible = WindowInsets.isImeVisible

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(navController)
        },
        floatingActionButton = {
            FloatingSave {
                focusManager.clearFocus()
                model.save()
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text("Workout name")
                },
                value = state.workout.name,
                isError = state.nameError,
                onValueChange = model::setName,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        model.validateName()
                    }
                )
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = "Exercises:"
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                label = {
                    Text("Exercise name")
                },
                value = state.item,
                isError = state.itemError,
                onValueChange = model::setItem,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        state.item.isBlank().then {
                            focusManager.clearFocus()
                            model.resetItem()
                        }.otherwise {
                            model.addItem()
                        }
                    }
                )
            )

            ItemsList(
                items = state.workout.items,
                onDelete = model::deleteItem,
                onClick = model::editItem,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(Unit) {
        model.actions.flowWithLifecycle(lifecycleOwner.lifecycle).collect { action ->
            when (action) {
                WorkoutAction.Back -> {
                    navController.previousBackStackEntry?.savedStateHandle?.set("workout-saved", true)
                    navController.popBackStack()
                }
                WorkoutAction.Edit -> focusRequester.requestFocus()
            }
        }
    }
}
