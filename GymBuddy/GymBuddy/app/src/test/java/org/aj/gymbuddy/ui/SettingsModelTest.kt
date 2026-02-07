package org.aj.gymbuddy.ui.screen.settings

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.aj.gymbuddy.db.SettingsConstants
import org.aj.gymbuddy.db.SettingsDao
import org.aj.gymbuddy.db.SettingsEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.*
import org.robolectric.RobolectricTestRunner
import java.util.UUID

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class SettingsModelTest {

    private lateinit var mockDao: SettingsDao

    private lateinit var settingsModel: SettingsModel

    @Before
    fun setup() {
        mockDao = mock()

        whenever(mockDao.selectAll()).thenReturn(
            flowOf(emptyList(), listOf(
                SettingsEntity(id = SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS, name = "Enable recommended workouts", value = true),
                SettingsEntity(id = SettingsConstants.ENABLE_TEST, name = "Enable setting1", value = true),
                SettingsEntity(id = SettingsConstants.ENABLE_TEST2, "Enable setting2", value = false)
            ))
        )
    }

    @Test
    fun `init inserts default settings if database is empty`() = runTest {
        settingsModel = SettingsModel(mockDao)

        advanceUntilIdle()

        verify(mockDao).upsert(SettingsEntity(id = SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS, name = "Enable recommended workouts", value = true))
        verify(mockDao).upsert(SettingsEntity(id = SettingsConstants.ENABLE_TEST, name = "Enable setting1", value = true))
        verify(mockDao).upsert(SettingsEntity(id = SettingsConstants.ENABLE_TEST2, "Enable setting2", value = false))

        verify(mockDao, atLeastOnce()).selectAll()
    }

    @Test
    fun `init observes settings and updates state`() = runTest {
        whenever(mockDao.selectAll()).thenReturn(
            flowOf(listOf(
                SettingsEntity(id = UUID.randomUUID(), name = "Setting 1", value = true),
                SettingsEntity(id = UUID.randomUUID(), name = "Setting 2", value = false)
            ))
        )

        settingsModel = SettingsModel(mockDao)

        advanceUntilIdle()

        val state = settingsModel.state.value

        assertEquals(2, state.settings.size)
        assertEquals("Setting 1", state.settings.first().name)
        assertEquals(true, state.settings.first().value)
        assertEquals("Setting 2", state.settings.last().name)
        assertEquals(false, state.settings.last().value)

        verify(mockDao, atLeastOnce()).selectAll()
    }


    @Test
    fun `insertSetting calls dao insert`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingToInsert = SettingsEntity(id = UUID.randomUUID(), name = "New Setting", value = true)

        settingsModel.insertSetting(settingToInsert)

        verify(mockDao).insert(settingToInsert)
    }

    @Test
    fun `upsertSetting calls dao upsert`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingToUpsert = SettingsEntity(id = UUID.randomUUID(), name = "Upserted Setting", value = false)

        settingsModel.upsertSetting(settingToUpsert)

        verify(mockDao).upsert(settingToUpsert)
    }

    @Test
    fun `deleteSetting calls dao delete`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingToDelete = SettingsEntity(id = UUID.randomUUID(), name = "Setting to Delete", value = true)

        settingsModel.deleteSetting(settingToDelete)

        verify(mockDao).delete(settingToDelete)
    }

    @Test
    fun `getSettingValueById returns value when setting exists`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingId = UUID.randomUUID()
        val settingEntity = SettingsEntity(id = settingId, name = "My Setting", value = true)

        whenever(mockDao.select(settingId)).thenReturn(flowOf(settingEntity))

        val value = settingsModel.getSettingValueById(settingId)

        assertEquals(true, value)
        verify(mockDao).select(settingId)
    }

    @Test
    fun `getSettingValueById returns null when setting does not exist`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingId = UUID.randomUUID()

        whenever(mockDao.select(settingId)).thenReturn(flowOf()) // Empty flow

        val value = settingsModel.getSettingValueById(settingId)

        assertNull(value)
        verify(mockDao).select(settingId)
    }

    @Test
    fun `getSettingValueById returns null when dao select throws exception`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingId = UUID.randomUUID()

        whenever(mockDao.select(settingId)).thenThrow(RuntimeException("DB Error"))

        val value = settingsModel.getSettingValueById(settingId)

        assertNull(value)
        verify(mockDao).select(settingId)
    }

    @Test
    fun `getNameValueById returns name when setting exists`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingId = UUID.randomUUID()
        val settingEntity = SettingsEntity(id = settingId, name = "My Setting Name", value = true)

        whenever(mockDao.select(settingId)).thenReturn(flowOf(settingEntity))

        val name = settingsModel.getNameValueById(settingId)

        assertEquals("My Setting Name", name)
        verify(mockDao).select(settingId)
    }

    @Test
    fun `getNameValueById returns null when setting does not exist`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingId = UUID.randomUUID()

        whenever(mockDao.select(settingId)).thenReturn(flowOf()) // Empty flow

        val name = settingsModel.getNameValueById(settingId)

        assertNull(name)
        verify(mockDao).select(settingId)
    }

    @Test
    fun `getNameValueById returns null when dao select throws exception`() = runTest {
        settingsModel = SettingsModel(mockDao)

        val settingId = UUID.randomUUID()

        whenever(mockDao.select(settingId)).thenThrow(RuntimeException("DB Error"))

        val name = settingsModel.getNameValueById(settingId)

        assertNull(name)
        verify(mockDao).select(settingId)
    }
}
