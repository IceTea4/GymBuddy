package org.aj.gymbuddy.ui.screen.workoutsedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.aj.gymbuddy.db.WorkoutDao
import org.aj.gymbuddy.db.WorkoutEntity
import java.util.UUID

class WorkoutsEditViewModel(
    private val dao: WorkoutDao,
    workoutId: String
) : ViewModel() {

    private val _state = MutableStateFlow(WorkoutsEditState())
    val state = _state.asStateFlow()

    init {
        val uuid = UUID.fromString(workoutId)
        viewModelScope.launch {
            dao.select(uuid).collectLatest { workout ->
                _state.value = WorkoutsEditState(
                    workout = workout,
                    isLoading = false
                )
            }
        }
    }

    fun markExerciseForDeletion(exercise: String) {
        val currentSet = _state.value.itemsMarkedForDeletion
        val updatedSet = if (exercise in currentSet) {
            currentSet - exercise
        } else {
            currentSet + exercise
        }

        _state.value = _state.value.copy(itemsMarkedForDeletion = updatedSet)
    }


    fun toggleEditMode() {
        _state.value = _state.value.copy(isEditing = !_state.value.isEditing)
    }

    fun cancelEdit() {
        val uuid = _state.value.workout?.id ?: return
        viewModelScope.launch {
            dao.select(uuid).collectLatest { workout ->
                _state.value = WorkoutsEditState(
                    workout = workout,
                    isLoading = false,
                    isEditing = false,
                    itemsMarkedForDeletion = emptySet()
                )
                return@collectLatest
            }
        }
    }

    fun saveChanges() {
        val workout = _state.value.workout ?: return
        val toRemove = _state.value.itemsMarkedForDeletion

        val updatedWorkout = workout.copy(items = workout.items.filterNot { it in toRemove })

        viewModelScope.launch {
            dao.upsert(updatedWorkout)
            _state.value = _state.value.copy(
                workout = updatedWorkout,
                isEditing = false,
                itemsMarkedForDeletion = emptySet()
            )
        }
    }

    fun updateWorkoutName(newName: String) {
        val currentWorkout = _state.value.workout ?: return
        val updated = currentWorkout.copy(name = newName)
        _state.value = _state.value.copy(workout = updated)
    }

    fun addEmptyExercise(): String {
        val newExercise = "New Exercise"
        val workout = _state.value.workout ?: return newExercise
        val updated = workout.copy(items = workout.items + newExercise)
        _state.value = _state.value.copy(workout = updated)
        return newExercise
    }

    fun updateExerciseName(oldName: String, newName: String) {
        val workout = _state.value.workout ?: return
        val updatedItems = workout.items.map { if (it == oldName) newName else it }
        val updated = workout.copy(items = updatedItems)
        _state.value = _state.value.copy(workout = updated)
    }

    fun removeExercise(exercise: String) {
        val workout = _state.value.workout ?: return
        val updatedWorkout = workout.copy(
            items = workout.items.filter { it != exercise }
        )
        _state.value = _state.value.copy(workout = updatedWorkout)

        viewModelScope.launch {
            dao.upsert(updatedWorkout)
        }
    }
}
