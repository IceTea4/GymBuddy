package org.aj.gymbuddy.db

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "org.aj.gymbuddy.db"
        )
            //.fallbackToDestructiveMigration() // Clear the database if needed
            .build()
    }

    single {
        get<Database>().workouts()
    }
    single {
        get<Database>().settings()
    }
}
