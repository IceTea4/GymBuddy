package org.aj.gymbuddy.db

import org.aj.gymbuddy.db.ExerciseCategory.Exercise

enum class RecommendedWorkouts(
    val title: String,
    val exercises: List<Exercise>
) {
    PushAbsDay("Push & Abs day", listOf(
        // Chest
        ExerciseCategory.Chest.exercises[0],
        ExerciseCategory.Chest.exercises[1],
        ExerciseCategory.Chest.exercises[2],
        // Shoulders
        ExerciseCategory.Shoulders.exercises[0],
        ExerciseCategory.Shoulders.exercises[1],
        // Abs
        ExerciseCategory.Abs.exercises[0],
        ExerciseCategory.Abs.exercises[1],
        ExerciseCategory.Abs.exercises[2],
        ExerciseCategory.Abs.exercises[3],
        ExerciseCategory.Abs.exercises[4]
    )),

    PullHIITDay("Pull & HIIT day", listOf(
        // Back
        ExerciseCategory.Back.exercises[0],
        ExerciseCategory.Back.exercises[1],
        ExerciseCategory.Back.exercises[2],
        ExerciseCategory.Back.exercises[3],
        // Wrists
        ExerciseCategory.Wrists.exercises[0],
        // HIIT
        ExerciseCategory.HIIT.exercises[0],
        ExerciseCategory.HIIT.exercises[1],
        ExerciseCategory.HIIT.exercises[2],
        ExerciseCategory.HIIT.exercises[3],
        ExerciseCategory.HIIT.exercises[4]
    )),

    LegsDay("Legs day", listOf(
        ExerciseCategory.Legs.exercises[0],
        ExerciseCategory.Legs.exercises[1],
        ExerciseCategory.Legs.exercises[2],
        ExerciseCategory.Legs.exercises[3],
        ExerciseCategory.Legs.exercises[4],
        ExerciseCategory.Legs.exercises[5],
        ExerciseCategory.Legs.exercises[6],
        ExerciseCategory.Legs.exercises[7],
        ExerciseCategory.Legs.exercises[8],
        ExerciseCategory.Legs.exercises[9]
    )),

    ArmsAbsDay("Arms & Abs day", listOf(
        // Arms
        ExerciseCategory.Arms.exercises[0],
        ExerciseCategory.Arms.exercises[1],
        ExerciseCategory.Arms.exercises[2],
        ExerciseCategory.Arms.exercises[3],
        ExerciseCategory.Arms.exercises[4],
        // Abs
        ExerciseCategory.Abs.exercises[0],
        ExerciseCategory.Abs.exercises[1],
        ExerciseCategory.Abs.exercises[2],
        ExerciseCategory.Abs.exercises[3],
        ExerciseCategory.Abs.exercises[4]
    ));
}
