package org.aj.gymbuddy.ui

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.db.WorkoutDao
import org.aj.gymbuddy.ui.screen.workout.WorkoutModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@RunWith(RobolectricTestRunner::class)
class WorkoutModelTest {

    private lateinit var dao: WorkoutDao
    private lateinit var model: WorkoutModel

    @Before
    fun setup() {
        dao = mock()
        model = WorkoutModel(dao)
    }

    @Test
    fun `save with blank name sets nameError to true`() = runTest {
        model.setName("")
        model.save()

        val state = model.state.first()
        assertEquals(true, state.nameError)
    }

    @Test
    fun `save with existing name sets nameError to true`() = runTest {
        whenever(dao.existsByName("Duplicate Workout")).thenReturn(true)

        model.setName("Duplicate Workout")
        model.save()

        val state = model.state.first()
        assertEquals(true, state.nameError)
    }

    @Test
    fun `save with valid name saves workout and resets state`() = runBlocking {
        whenever(dao.existsByName(any())).thenReturn(false)

        model.setName("New Workout")
        model.save()

        val state = model.state.first()
        assertEquals("", state.workout.name)
        verify(dao).upsert(any())
    }

    @Test
    fun `validateName sets nameError to true for blank name`() = runTest {
        model.setName("")
        model.validateName()

        val state = model.state.first()
        assertEquals(true, state.nameError)
    }

    @Test
    fun `setName updates workout name and clears nameError`() = runTest {
        model.setName("Workout Name")

        val state = model.state.first()
        assertEquals("Workout Name", state.workout.name)
        assertEquals(false, state.nameError)
    }

    @Test
    fun `setItem updates item and clears itemError`() = runTest {
        model.setItem("New Item")

        val state = model.state.first()
        assertEquals("New Item", state.item)
        assertEquals(false, state.itemError)
    }

    @Test
    fun `validateItem sets itemError to true if item exists in workout`() = runTest {
        model.setItem("Duplicate Item")
        model.addItem()
        model.setItem("Duplicate Item")

        val isValid = model.validateItem()
        val state = model.state.first()

        assertEquals(true, state.itemError)
        assertEquals(true, isValid)
    }

    @Test
    fun `validateItem returns false for unique item`() = runTest {
        model.setItem("Unique Item")

        val isValid = model.validateItem()
        val state = model.state.first()

        assertEquals(false, state.itemError)
        assertEquals(false, isValid)
    }

    @Test
    fun `resetItem clears item and itemError`() = runTest {
        model.setItem("Item")
        model.resetItem()

        val state = model.state.first()
        assertEquals("", state.item)
        assertEquals(false, state.itemError)
    }

    @Test
    fun `addItem adds item to workout and clears item state`() = runTest {
        model.setItem("New Item")
        model.addItem()

        val state = model.state.first()
        assertEquals(listOf("New Item"), state.workout.items)
        assertEquals("", state.item)
        assertEquals(false, state.itemError)
    }

    @Test
    fun `addItem trims whitespace from item before adding to workout`() = runTest {
        model.setItem("  Trimmed Item  ")
        model.addItem()

        val state = model.state.first()
        assertEquals(listOf("Trimmed Item"), state.workout.items)
        assertEquals("", state.item)
        assertEquals(false, state.itemError)
    }

    @Test
    fun `deleteItem removes item from workout`() = runTest {
        model.setItem("Item to Delete")
        model.addItem()
        model.deleteItem("Item to Delete")

        val state = model.state.first()
        assertEquals(emptyList<String>(), state.workout.items)
    }

    @Test
    fun `editItem sets item for editing and removes it from workout`() = runTest {
        model.setItem("Item to Edit")
        model.addItem()
        model.editItem("Item to Edit")

        val state = model.state.first()
        assertEquals("Item to Edit", state.item)
        assertEquals(emptyList<String>(), state.workout.items)
    }

    @Test
    fun `editItem does nothing if item field is already populated`() = runTest {
        model.setItem("Existing Item")
        model.addItem()
        model.setItem("New Item")

        model.editItem("Existing Item")

        val state = model.state.first()
        assertEquals(listOf("Existing Item"), state.workout.items)
        assertEquals("New Item", state.item)
    }

    @Test
    fun `setSelected updates workout with selected exercises`() = runTest {
        val exercises = listOf(
            ExerciseCategory.Exercise.Rowing,
            ExerciseCategory.Exercise.PullUps
        )

        model.setSelected(exercises)

        val state = model.state.first()
        assertEquals(listOf("Rowing", "PullUps"), state.workout.items)
    }

    @Test
    fun `setSelected clears workout items when given an empty list`() = runTest {
        val exercises = emptyList<ExerciseCategory.Exercise>()

        model.setSelected(exercises)

        val state = model.state.first()
        assertEquals(emptyList<String>(), state.workout.items)
    }
}
