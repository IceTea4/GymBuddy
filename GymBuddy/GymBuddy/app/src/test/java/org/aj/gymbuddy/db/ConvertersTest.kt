package org.aj.gymbuddy.db

import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun `toItems should convert a comma-separated string to a list of strings`() {
        val input = "item1,item2,item3"
        val expected = listOf("item1", "item2", "item3")
        assertEquals(expected, converters.toItems(input))
    }

    @Test
    fun `toItems should return an empty list for an empty string`() {
        val input = ""
        val expected = emptyList<String>()
        assertEquals(expected, converters.toItems(input))
    }

    @Test
    fun `toItems should return an empty list for a null string`() {
        val input: String? = null
        val expected = emptyList<String>()
        assertEquals(expected, converters.toItems(input ?: ""))
    }

    @Test
    fun `toString should convert a list of strings to a comma-separated string`() {
        val input = listOf("item1", "item2", "item3")
        val expected = "item1,item2,item3"
        assertEquals(expected, converters.toString(input))
    }

    @Test
    fun `toString should return an empty string for an empty list`() {
        val input = emptyList<String>()
        val expected = ""
        assertEquals(expected, converters.toString(input))
    }
}
