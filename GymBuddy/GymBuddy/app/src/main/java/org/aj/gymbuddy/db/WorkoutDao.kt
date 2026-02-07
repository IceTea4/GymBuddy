package org.aj.gymbuddy.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface WorkoutDao {

    @Transaction
    suspend fun upsert(data: WorkoutEntity) {
        val updated = update(data)

        if (updated == 0) {
            insert(data)
        }
    }

    @Insert
    suspend fun insert(data: WorkoutEntity)

    @Update
    suspend fun update(data: WorkoutEntity): Int

    @Delete
    suspend fun delete(data: WorkoutEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM workout WHERE name = :name)")
    suspend fun existsByName(name: String): Boolean

    @Query("SELECT * FROM workout WHERE id=:id")
    fun select(id: UUID): Flow<WorkoutEntity>

    @Query("SELECT * FROM workout ORDER BY name COLLATE LOCALIZED")
    fun selectAll(): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workout WHERE LOWER(name) LIKE LOWER('%' || :name || '%') ORDER BY name COLLATE LOCALIZED")
    fun selectLike(name: String): Flow<List<WorkoutEntity>>
}
