package org.aj.gymbuddy.db

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toItems(items: String): List<String> = items.takeIf { items.isNotEmpty() }
        ?.split(",")
        ?: emptyList()

    @TypeConverter
    fun toString(items: List<String>): String = items.joinToString(",")
}