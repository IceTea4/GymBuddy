package org.aj.gymbuddy.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
class WorkoutDaoTest {

    private lateinit var database: Database

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `upsert inserts new row`() = runBlocking {
        val row = WorkoutEntity(
            id = UUID.randomUUID(),
            name = "Test Workout",
            items = listOf("Item 1", "Item 2")
        )

        database.workouts().upsert(row)

        assertEquals(1, database.workouts().selectAll().first().size)
    }

    @Test
    fun `upsert updates old row`() = runBlocking {
        val old = WorkoutEntity(
            id = UUID.randomUUID(),
            name = "Test Workout",
            items = listOf("Item 1", "Item 2")
        )

        database.workouts().insert(old)

        val new = old.copy(
            name = "Updated Workout",
            items = listOf("Item 3", "Item 4")
        )

        database.workouts().upsert(new)

        assertEquals(1, database.workouts().selectAll().first().size)
        val result = database.workouts().selectAll().first().first()

        assertEquals(old.id, result.id)
        assertEquals(new.name, result.name)
        assertEquals(new.items, result.items)
    }
}
