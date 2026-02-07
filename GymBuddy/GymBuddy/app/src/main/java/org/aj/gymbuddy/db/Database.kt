package org.aj.gymbuddy.db

import androidx.room.Database
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 2,
    entities = [
        WorkoutEntity::class,
        SettingsEntity::class
    ],
    exportSchema = false
)
@RewriteQueriesToDropUnusedColumns
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun workouts(): WorkoutDao
    abstract fun settings(): SettingsDao
}
