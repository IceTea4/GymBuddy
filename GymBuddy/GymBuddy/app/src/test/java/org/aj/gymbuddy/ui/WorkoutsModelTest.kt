package org.aj.gymbuddy.ui

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.aj.gymbuddy.db.WorkoutDao
import org.aj.gymbuddy.db.WorkoutEntity
import org.aj.gymbuddy.ui.screen.workouts.WorkoutsModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.*
import org.robolectric.RobolectricTestRunner
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
class WorkoutsModelTest {

    private lateinit var dao: WorkoutDao
    private lateinit var model: WorkoutsModel

    @Before
    fun setup() {
        dao = mock()
        whenever(dao.selectAll()).thenReturn(flowOf(emptyList()))
        model = WorkoutsModel(dao)
    }

    @Test
    fun `init sets empty workout list from dao`() = runTest {
        val state = model.state.first()
        assertEquals(emptyList<WorkoutEntity>(), state.workouts)
    }

    @Test
    fun `init sets workout list from dao`() = runTest {
        val workouts = listOf(
            WorkoutEntity(name = "Workout A", items = listOf("Pushups")),
            WorkoutEntity(name = "Workout B", items = listOf("Pullups"))
        )
        whenever(dao.selectAll()).thenReturn(flowOf(workouts))
        model = WorkoutsModel(dao)

        val state = model.state.first()
        assertEquals(workouts, state.workouts)
    }
    @Test
    fun `insertWorkoutPlan adds workout`() = runTest {
        val workout = WorkoutEntity(name = "Workout X", items = listOf("Squats"))
        model.insertWorkoutPlan(workout)
        verify(dao).insert(workout)
    }

    @Test
    fun `deleteWorkoutPlan removes workout`() = runTest {
        val workout = WorkoutEntity(name = "Workout Y", items = listOf("Lunges"))
        model.deleteWorkoutPlan(workout)
        verify(dao).delete(workout)
    }

    @Test
    fun `selectWorkoutPlan calls DAO with correct ID`() = runTest {
        val id = UUID.randomUUID()
        model.selectWorkoutPlan(id)
        verify(dao).select(id)
    }

}
