package org.aj.gymbuddy.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String,
    val value: Boolean = true
)
