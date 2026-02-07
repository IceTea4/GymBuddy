package org.aj.gymbuddy.ui.screen.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.db.WorkoutDao
import org.aj.gymbuddy.db.WorkoutEntity
import org.aj.gymbuddy.lang.then

class WorkoutModel(
    val dao: WorkoutDao
) : ViewModel() {

    private val actionsChannel = Channel<WorkoutAction>(Channel.BUFFERED)
    private val stateFlow = MutableStateFlow(WorkoutState(newItem()))
    val actions = actionsChannel.receiveAsFlow()
    val state = stateFlow.asStateFlow()

    private var stateValue: WorkoutState
        get() = stateFlow.value
        set(value) = stateFlow.update { value }

    fun save() {
        if (stateValue.workout.name.isBlank()) {
            stateValue = stateValue.copy(
                nameError = true
            )
        } else {
            stateValue = stateValue.copy(
                workout = stateValue.workout.trim()
            )

            viewModelScope.launch {
                if (dao.existsByName(stateValue.workout.name)) {
                    stateValue = stateValue.copy(
                        nameError = true
                    )
                } else {
                    dao.upsert(stateValue.workout)
                    stateValue = WorkoutState(workout = newItem())
                    WorkoutAction.Back.send()
                }
            }
        }
    }

    fun validateName() {
        if (stateValue.workout.name.isBlank()) {
            stateValue = stateValue.copy(
                nameError = true
            )
        }
    }

    fun setName(value: String) {
//        if (stateValue.selected.isEmpty()) {
            stateValue = stateValue.copy(
                workout = stateValue.workout.copy(name = value),
                nameError = false
            )
//        } else {
//            viewModelScope.launch {
//                stateValue = stateValue.copy(
//                    workout = stateValue.workout.copy(name = value),
//                    nameError = false,
//                    likes = dao.selectLike(value).first()
//                )
//            }
//        }
    }

//    fun selectWorkout(workout: WorkoutEntity) {
//        stateValue = stateValue.copy(
//            workout = workout.copy(
//                items = (workout.items + stateValue.workout.items).distinct(),
//            ),
//            nameError = false,
//        )
//    }

    fun setItem(value: String) {
        stateValue = stateValue.copy(
            item = value,
            itemError = false
        )
    }

    fun validateItem(): Boolean {
        return (stateValue.item in stateValue.workout.items).then {
            stateValue = stateValue.copy(
                itemError = true
            )
        }
    }

    fun resetItem() {
        stateValue = stateValue.copy(
            item = "",
            itemError = false
        )
    }

    fun addItem() {
        if (validateItem()) {
            return
        }

        stateValue = stateValue.copy(
            workout = stateValue.workout.copy(
                items = stateValue.workout.items + stateValue.item.trim()
            ),
            item = "",
            itemError = false
        )
    }

    fun deleteItem(item: String) {
        val workout = stateValue.workout
        val updatedList = workout.items - item
        val updatedWorkout = workout.copy(items = updatedList)

        stateValue = stateValue.copy(
            workout = updatedWorkout
        )
    }

    fun editItem(item: String) {
        if (stateValue.item.isNotBlank()) {
            return
        }

        deleteItem(item)

        stateValue = stateValue.copy(
            item = item
        )

        WorkoutAction.Edit.send()
    }

    fun setSelected(selectedExercises: List<ExerciseCategory.Exercise>) {
        stateValue = WorkoutState(
            workout = WorkoutEntity(
                name = "",
                items = selectedExercises.map { it.name }
            ),
            selected = selectedExercises,
        )
    }

    private fun WorkoutAction.send() {
        viewModelScope.launch {
            actionsChannel.send(this@send)
        }
    }
}

private fun WorkoutEntity.trim() = copy(
    name = name.trim(),
)

private fun newItem() = WorkoutEntity(
    name = "",
    items = emptyList()
)
