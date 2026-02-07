package org.aj.gymbuddy.db

import java.util.UUID

object SettingsConstants {
    val ENABLE_RECOMMENDED_WORKOUTS: UUID = UUID.fromString("11111111-1111-1111-1111-111111111111")
    val ENABLE_TEST: UUID = UUID.fromString("22222222-2222-2222-2222-222222222222")
    val ENABLE_TEST2: UUID = UUID.fromString("33333333-3333-3333-3333-333333333333")
    val ENABLE_DARK_MODE: UUID = UUID.nameUUIDFromBytes("dark_mode".toByteArray())
}
