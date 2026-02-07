package org.aj.gymbuddy.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.aj.gymbuddy.db.SettingsConstants
import org.aj.gymbuddy.db.SettingsDao
import org.aj.gymbuddy.db.SettingsEntity
import java.util.UUID
import kotlinx.coroutines.flow.*

class SettingsModel(
    val dao: SettingsDao
) : ViewModel() {

    private val stateFlow = MutableStateFlow(SettingsState())
    val state = stateFlow.asStateFlow()

    val isDarkThemeEnabledFlow: StateFlow<Boolean> =
        state.map { settings ->
            settings.settings.find { it.id == SettingsConstants.ENABLE_DARK_MODE }?.value ?: false
        }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isDarkThemeEnabled: Boolean
        get() = stateValue.settings.find { it.id == SettingsConstants.ENABLE_DARK_MODE }?.value ?: false

    private var stateValue: SettingsState
        get() = stateFlow.value
        set(value) = stateFlow.update { value }


    init {
        viewModelScope.launch {
            dao.selectAll().first().let { settings ->
                if (settings.isEmpty()) {
                    dao.upsert(SettingsEntity(id = SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS, name = "Enable recommended workouts", value = true))
                    dao.upsert(SettingsEntity(id = SettingsConstants.ENABLE_TEST, name = "Enable setting1", value = true))
                    dao.upsert(SettingsEntity(id = SettingsConstants.ENABLE_TEST2, "Enable setting2", value = false))
                    dao.upsert(SettingsEntity(id = SettingsConstants.ENABLE_DARK_MODE, name = "Dark Mode", value = false))
                }
            }

            dao.selectAll()
                .onEach { settings ->
                    stateValue = stateValue.copy(settings = settings)
                }
                .launchIn(viewModelScope)
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            upsertSetting(
                SettingsEntity(
                    id = SettingsConstants.ENABLE_DARK_MODE,
                    name = "Dark Mode",
                    value = enabled
                )
            )
            stateValue = stateValue.copy(
                settings = stateValue.settings.map {
                    if (it.id == SettingsConstants.ENABLE_DARK_MODE) it.copy(value = enabled) else it
                }
            )
        }
    }

    suspend fun insertSetting(setting: SettingsEntity) {
        dao.insert(setting)
    }
    suspend fun upsertSetting(setting: SettingsEntity) {
        dao.upsert(setting)
    }
    suspend fun deleteSetting(setting: SettingsEntity) {
        dao.delete(setting)
    }
    suspend fun getSettingValueById(id: UUID): Boolean? {
        return try {
            dao.select(id).first().value
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getNameValueById(id: UUID): String? {
        return try {
            dao.select(id).first().name
        } catch (e: Exception) {
            null
        }
    }
}
