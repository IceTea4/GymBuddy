package org.aj.gymbuddy.db

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecommendedWorkoutsTest {

    @Test
    fun `test all recommended workouts are defined`() {
        val allWorkouts = RecommendedWorkouts.values()
        assertEquals(4, allWorkouts.size)
        assertTrue(allWorkouts.contains(RecommendedWorkouts.PushAbsDay))
        assertTrue(allWorkouts.contains(RecommendedWorkouts.PullHIITDay))
        assertTrue(allWorkouts.contains(RecommendedWorkouts.LegsDay))
        assertTrue(allWorkouts.contains(RecommendedWorkouts.ArmsAbsDay))
    }
}
