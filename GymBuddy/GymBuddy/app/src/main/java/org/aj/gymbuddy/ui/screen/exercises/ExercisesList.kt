package org.aj.gymbuddy.ui.screen.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.aj.gymbuddy.db.ExerciseCategory

@Composable
fun ExercisesList(
    exercises: List<ExerciseCategory.Exercise>,
    selected: List<ExerciseCategory.Exercise>,
    onTap: (ExerciseCategory.Exercise) -> Unit,
    onLongPress: (ExerciseCategory.Exercise) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = exercises,
            key = { it.id },
        ) { exercise ->
            Column( modifier = Modifier.padding(vertical = 2.dp)) {
                ExercisesRow(
                    exercise = exercise,
                    canSelect = selected.isNotEmpty(),
                    isSelected = selected.contains(exercise),
                    onTap = { onTap(exercise) },
                    onLongPress = { onLongPress(exercise) },
                )
            }
        }
    }
}
