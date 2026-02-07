package org.aj.gymbuddy

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.aj.gymbuddy.db.Database
import org.aj.gymbuddy.db.WorkoutDao
import org.aj.gymbuddy.db.WorkoutEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import java.util.UUID
import kotlin.random.Random
import kotlin.system.measureTimeMillis

const val TOTAL_OPERATIONS_ROBO = 1000
const val DURATION_MINUTES_ROBO = 5
const val DURATION_SECONDS_ROBO = DURATION_MINUTES_ROBO * 60
const val OPERATIONS_PER_SECOND_TARGET_ROBO = TOTAL_OPERATIONS_ROBO.toDouble() / DURATION_SECONDS_ROBO

data class OperationResultRobo(
    val type: String,
    val durationMillis: Long,
    val success: Boolean,
    val exception: Throwable? = null
)

@RunWith(RobolectricTestRunner::class)
class WorkoutDaoLoadTest {

    private lateinit var db: Database
    private lateinit var workoutDao: WorkoutDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries()
            .build()
        workoutDao = db.workouts()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun simulateLoadOnWorkoutDaoWithRobolectric() = runBlocking {
        println("Starting Load Test with Robolectric")

        val allOperationResults = mutableListOf<OperationResultRobo>()
        val insertedIds = mutableListOf<UUID>()

        val testDispatcher = Dispatchers.IO.limitedParallelism(10)

        val testStartTime = System.currentTimeMillis()

        val jobs = List(TOTAL_OPERATIONS_ROBO) { index ->
            launch(testDispatcher) {
                val beforeDelay = System.currentTimeMillis()
                if (index > 0 && TOTAL_OPERATIONS_ROBO > 0) {
                    val delayMillis = Random.nextLong(0, (DURATION_SECONDS_ROBO * 1000L).let { if (it == 0L || TOTAL_OPERATIONS_ROBO == 0) 1L else it / TOTAL_OPERATIONS_ROBO })
                    delay(delayMillis)
                }
                val afterDelay = System.currentTimeMillis()

                val operationType = if (Random.nextBoolean() && insertedIds.isNotEmpty()) "read" else "insert"
                var operationDuration: Long = 0
                var success = false
                var opException: Throwable? = null

                try {
                    when (operationType) {
                        "insert" -> {
                            val newId = UUID.randomUUID()
                            val entity = WorkoutEntity(
                                id = newId,
                                name = "RoboTestWorkout_${System.currentTimeMillis()}_${Random.nextInt(10000)}",
                                items = listOf("asd", "wasd")
                            )
                            operationDuration = measureTimeMillis {
                                workoutDao.insert(entity)
                            }
                            synchronized(insertedIds) {
                                insertedIds.add(newId)
                            }
                            success = true
                        }
                        "read" -> {
                            val idToRead: UUID?
                            synchronized(insertedIds) {
                                idToRead = if (insertedIds.isNotEmpty()) insertedIds.random() else null
                            }

                            if (idToRead != null) {
                                var entityRead: WorkoutEntity? = null
                                operationDuration = measureTimeMillis {
                                    entityRead = workoutDao.select(idToRead!!).first()
                                }
                                success = entityRead != null
                                if (!success) {
                                    opException = IllegalStateException("Robo: Failed to read entity: $idToRead")
                                }
                            } else {
                                // Fallback insert
                                val newId = UUID.randomUUID()
                                val entity = WorkoutEntity(
                                    id = newId,
                                    name = "RoboTestWorkout_Fallback_${System.currentTimeMillis()}_${Random.nextInt(10000)}",
                                    items = listOf("asd", "wasd")
                                )
                                operationDuration = measureTimeMillis {
                                    workoutDao.insert(entity)
                                }
                                synchronized(insertedIds) {
                                    insertedIds.add(newId)
                                }
                                success = true
                            }
                        }
                        else -> opException = IllegalStateException("Unknown operation type")
                    }
                    allOperationResults.add(OperationResultRobo(operationType, operationDuration, success, opException))
                } catch (e: Exception) {
                    opException = e
                    allOperationResults.add(OperationResultRobo(operationType, operationDuration, false, opException))
                }
            }
        }

        jobs.joinAll()

        val testEndTime = System.currentTimeMillis()
        val totalTestDurationMillis = testEndTime - testStartTime
        val totalTestDurationSeconds = totalTestDurationMillis / 1000.0

        println("--------------------------------------------------")
        println("Robolectric Load Test Finished in ${String.format("%.2f", totalTestDurationSeconds)} seconds.")
        val successfulOperations = allOperationResults.filter { it.success }
        val failedOperations = allOperationResults.filter { !it.success }

        println("Total Operations Attempted: ${allOperationResults.size}")
        println("Successful Operations: ${successfulOperations.size}")
        println("Failed Operations: ${failedOperations.size}")
        if (successfulOperations.isNotEmpty()) {
            val avgResponseTime = successfulOperations.map { it.durationMillis }.average()
            val minResponseTime = successfulOperations.minOfOrNull { it.durationMillis } ?: 0
            val maxResponseTime = successfulOperations.maxOfOrNull { it.durationMillis } ?: 0

            println("Response Time (for successful operations):")
            println("  Average: ${String.format("%.2f", avgResponseTime)} ms")
            println("  Min: $minResponseTime ms")
            println("  Max: $maxResponseTime ms")
            // ... etc. ...
            val throughput = successfulOperations.size / totalTestDurationSeconds
            println("Throughput (successful ops/sec): ${String.format("%.2f", throughput)}")
        } else {
            println("No successful operations to calculate metrics.")
        }
        println("--------------------------------------------------")
        println("Observed Behavior (Inferred - Robolectric):")
        if (failedOperations.isNotEmpty()) {
            println("  - High number of errors (${failedOperations.size}) might indicate instability.")
        }
        println("--------------------------------------------------")

    }
}