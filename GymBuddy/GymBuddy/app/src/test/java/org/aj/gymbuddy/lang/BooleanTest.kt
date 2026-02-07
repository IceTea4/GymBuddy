package org.aj.gymbuddy.lang

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BooleanTest {

    @Test
    fun `then executes block when true`() {
        var executed = false
        true.then { executed = true }
        assertTrue(executed)
    }

    @Test
    fun `then does not execute block when false`() {
        var executed = false
        false.then { executed = true }
        assertFalse(executed)
    }

    @Test
    fun `then returns the original boolean value when true`() {
        val result = true.then {}
        assertTrue(result)
    }

    @Test
    fun `then returns the original boolean value when false`() {
        val result = false.then {}
        assertFalse(result)
    }

    @Test
    fun `otherwise executes block when false`() {
        var executed = false
        false.otherwise { executed = true }
        assertTrue(executed)
    }

    @Test
    fun `otherwise does not execute block when true`() {
        var executed = false
        true.otherwise { executed = true }
        assertFalse(executed)
    }

    @Test
    fun `otherwise returns the original boolean value when false`() {
        val result = false.otherwise {}
        assertFalse(result)
    }

    @Test
    fun `otherwise returns the original boolean value when true`() {
        val result = true.otherwise {}
        assertTrue(result)
    }
}
