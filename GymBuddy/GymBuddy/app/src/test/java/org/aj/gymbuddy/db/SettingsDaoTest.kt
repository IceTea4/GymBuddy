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
class SettingsDaoTest {
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
        val row = SettingsEntity(
            id = UUID.randomUUID(),
            name = "Test Settings",
            value = true
        )

        database.settings().upsert(row)

        assertEquals(1, database.settings().selectAll().first().size)
    }

    @Test
    fun `upsert updates old row`() = runBlocking {
        val old = SettingsEntity(
            id = UUID.randomUUID(),
            name = "Test Settings",
            value = true
        )

        database.settings().insert(old)

        val new = old.copy(
            name = "Updated Settings",
            value = false
        )

        database.settings().upsert(new)

        assertEquals(1, database.settings().selectAll().first().size)
        val result = database.settings().selectAll().first().first()

        assertEquals(old.id, result.id)
        assertEquals(new.name, result.name)
        assertEquals(new.value, result.value)
    }
}
