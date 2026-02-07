package org.aj.gymbuddy.ui.screen.exercises

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.aj.gymbuddy.db.ExerciseCategory

class ExercisesModel: ViewModel() {

    private val stateFlow = MutableStateFlow(ExercisesState())
    val state = stateFlow.asStateFlow()

    private var stateValue: ExercisesState
        get() = stateFlow.value
        set(value) = stateFlow.update { value }

    fun expand(category: ExerciseCategory) {
        if (stateValue.selected.isNotEmpty() && stateValue.selected.any { it.category == category }) {
            return
        }

        if (stateValue.selected.isNotEmpty()) {
            expandInSelectionMode(category)
        } else {
            expandInSimpleMode(category)
        }
    }

    private fun expandInSelectionMode(category: ExerciseCategory) {
        if (stateValue.expanded.contains(category)) {
            stateValue = stateValue.copy(
                expanded = stateValue.expanded - category
            )
        } else {
            stateValue = stateValue.copy(
                expanded = stateValue.expanded + category
            )
        }
    }

    private fun expandInSimpleMode(category: ExerciseCategory) {
        if (stateValue.expanded.contains(category)) {
            stateValue = stateValue.copy(
                expanded = emptyList()
            )
        } else {
            stateValue = stateValue.copy(
                expanded = listOf(category)
            )
        }
    }

    fun setFilter(text: String) {
        stateValue = stateValue.copy(
            filter = text,
            expanded = emptyList(),
            selected = emptyList()
        )
    }

    fun select(exercise: ExerciseCategory.Exercise) {
        if (stateValue.selected.contains(exercise)) {
            stateValue = stateValue.copy(
                selected = stateValue.selected - exercise,
                expanded = if (stateValue.selected.size == 1) {
                    listOf(exercise.category)
                } else {
                    stateValue.expanded
                }
            )
        } else {
            stateValue = stateValue.copy(
                selected = stateValue.selected + exercise
            )
        }
    }

    fun clearSelection() {
        stateValue = stateValue.copy(
            selected = emptyList(),
            expanded = stateValue.selected.lastOrNull()?.let { listOf(it.category) } ?: emptyList()
        )
    }
}
