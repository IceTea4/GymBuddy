package org.aj.gymbuddy.ui.screen.workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.aj.gymbuddy.db.WorkoutDao
import org.aj.gymbuddy.db.WorkoutEntity
import java.util.UUID

class WorkoutsModel(
    val dao: WorkoutDao
) : ViewModel() {

    private val stateFlow = MutableStateFlow(WorkoutsState())
    val state = stateFlow.asStateFlow()

    private var stateValue: WorkoutsState
        get() = stateFlow.value
        set(value) = stateFlow.update { value }

    init {
        dao.selectAll()
            .onEach { workouts ->
                stateValue = stateValue.copy(
                    workouts = workouts
                )
            }.launchIn(viewModelScope)
    }

    suspend fun insertWorkoutPlan(plan: WorkoutEntity) {
        dao.insert(plan)
    }

    suspend fun deleteWorkoutPlan(plan: WorkoutEntity) {
        dao.delete(plan)
    }

    fun selectWorkoutPlan(id: UUID) {
        dao.select(id);
    }
}
