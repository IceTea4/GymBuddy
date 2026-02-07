package org.aj.gymbuddy.ui

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.ui.screen.exercises.ExercisesModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExercisesModelTest {

    private lateinit var model: ExercisesModel

    @Before
    fun setup() {
        model = ExercisesModel()
    }

    @Test
    fun `expand adds category to expanded list in simple mode`() = runBlocking {
        val category = ExerciseCategory.Warmup

        model.expand(category)

        val state = model.state.first()
        assertEquals(listOf(category), state.expanded)
    }

    @Test
    fun `expand removes category from expanded list in simple mode`() = runBlocking {
        val category = ExerciseCategory.Warmup

        model.expand(category) // Add first

        model.expand(category) // Remove

        val state = model.state.first()
        assertEquals(emptyList<ExerciseCategory>(), state.expanded)
    }

    @Test
    fun `expand adds category to expanded list in selection mode`() = runBlocking {
        val category1 = ExerciseCategory.Warmup
        val category2 = ExerciseCategory.Back
        val exercise = category1.exercises.first()

        model.select(exercise) // Enter selection mode
        model.expand(category2)

        val state = model.state.first()
        assertEquals(listOf(category2), state.expanded)
    }

    @Test
    fun `expand removes category from expanded list in selection mode`() = runBlocking {
        val category1 = ExerciseCategory.Warmup
        val category2 = ExerciseCategory.Back
        val exercise = category1.exercises.first()

        model.expand(category1) // Add first
        model.select(exercise) // Enter selection mode
        model.expand(category2) // Add
        model.expand(category2) // Remove

        val state = model.state.first()
        assertEquals(listOf(category1), state.expanded)
    }

    @Test
    fun `expand does not remove category from expanded list in selection mode`() = runBlocking {
        val category1 = ExerciseCategory.Warmup
        val category2 = ExerciseCategory.Back
        val exercise1 = category1.exercises.first()
        val exercise2 = category2.exercises.first()

        model.expand(category1) // Add first
        model.select(exercise1) // Enter selection mode
        model.expand(category2) // Add
        model.select(exercise2) // Select another exercise
        model.expand(category2) // Not able to close

        val state = model.state.first()
        assertEquals(listOf(category1,category2), state.expanded)
    }

    @Test
    fun `setFilter updates filter and clears expanded and selected lists`() = runBlocking {
        val category = ExerciseCategory.Warmup
        val exercise = category.exercises.first()

        model.select(exercise)
        model.expand(category)
        model.setFilter("New Filter")

        val state = model.state.first()
        assertEquals("New Filter", state.filter)
        assertEquals(emptyList<ExerciseCategory>(), state.expanded)
        assertEquals(emptyList<ExerciseCategory.Exercise>(), state.selected)
    }

    @Test
    fun `select adds exercise to selected list`() = runBlocking {
        val category = ExerciseCategory.Warmup
        val exercise = category.exercises.first()

        model.select(exercise)

        val state = model.state.first()
        assertEquals(listOf(exercise), state.selected)
    }

    @Test
    fun `select removes exercise from selected list`() = runBlocking {
        val category = ExerciseCategory.Warmup
        val exercise = category.exercises.first()

        model.select(exercise) // Add
        model.select(exercise) // Remove

        val state = model.state.first()
        assertEquals(emptyList<ExerciseCategory.Exercise>(), state.selected)
    }

    @Test
    fun `clearSelection clears selected list and updates expanded list`() = runBlocking {
        val category = ExerciseCategory.Warmup
        val exercise = category.exercises.first()

        model.select(exercise)
        model.clearSelection()

        val state = model.state.first()
        assertEquals(emptyList<ExerciseCategory.Exercise>(), state.selected)
        assertEquals(listOf(category), state.expanded)
    }
}
