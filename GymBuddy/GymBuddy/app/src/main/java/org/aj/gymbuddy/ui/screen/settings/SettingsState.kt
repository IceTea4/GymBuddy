package org.aj.gymbuddy.ui.screen.settings

import org.aj.gymbuddy.db.SettingsEntity

data class SettingsState(
    val settings: List<SettingsEntity> = emptyList(),
)
