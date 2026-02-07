package org.aj.gymbuddy.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "workout"
)
data class WorkoutEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val items: List<String>
)
