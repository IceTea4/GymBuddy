package org.aj.gymbuddy.db

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExerciseTest {
    @Test
    fun `test category collection`() {
        val warmup = ExerciseCategory.Exercise.entries
            .filter { it.category == ExerciseCategory.Warmup }

        assertTrue(warmup.containsAll(ExerciseCategory.Warmup.exercises))
    }

    @Test
    fun `test exercise category properties`() {
        ExerciseCategory.values().forEach { category ->
            assertTrue(category.id.toString().isNotEmpty())
            assertTrue(category.title.isNotBlank())
        }
    }

    @Test
    fun `test exercise properties`() {
        ExerciseCategory.Exercise.entries.forEach { exercise ->
            assertTrue(exercise.id.toString().isNotEmpty())
            assertTrue(exercise.title.isNotBlank())
            assertTrue(exercise.description.isNotBlank())
        }
    }

    @Test
    fun `test lazy initialization of exercises`() {
        ExerciseCategory.values().forEach { category ->
            assertEquals(
                ExerciseCategory.Exercise.entries.filter { it.category == category },
                category.exercises
            )
        }
    }

    @Test
    fun `test exercise toString returns title`() {
        val exercise = ExerciseCategory.Exercise.BenchPress
        assertEquals("Bench press", exercise.toString())
    }

    @Test
    fun `test unique exercise IDs`() {
        val allExercises = ExerciseCategory.Exercise.entries
        val uniqueIds = allExercises.map { it.id }.toSet()
        assertEquals(allExercises.size, uniqueIds.size)
    }
}
