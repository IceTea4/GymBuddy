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
interface SettingsDao {

    @Transaction
    suspend fun upsert(data: SettingsEntity) {
        val updated = update(data)

        if (updated == 0) {
            insert(data)
        }
    }

    @Insert
    suspend fun insert(data: SettingsEntity)

    @Update
    suspend fun update(data: SettingsEntity): Int

    @Delete
    suspend fun delete(data: SettingsEntity)

    @Query("SELECT * FROM settings WHERE id=:id")
    fun select(id: UUID): Flow<SettingsEntity>

    @Query("SELECT * FROM settings")
    fun selectAll(): Flow<List<SettingsEntity>>
}
