package org.aj.gymbuddy.ui.screen.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.ui.compose.DropDown

@Composable
fun CategoriesList(
    categories: List<ExerciseCategory>,
    expandedCategories: List<ExerciseCategory>,
    selected: List<ExerciseCategory.Exercise>,
    onCategoryClick: (ExerciseCategory) -> Unit,
    onTap: (ExerciseCategory.Exercise) -> Unit,
    onLongPress: (ExerciseCategory.Exercise) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 0.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(
            items = categories,
            key = { it.id },
        ) { category ->
            DropDown(
                title = category.title,
                items = category.exercises,
                isExpanded = expandedCategories.contains(category),
                onToggle = { onCategoryClick(category) },
                itemContent = {
                    ExercisesRow(
                        exercise = it,
                        canSelect = selected.isNotEmpty(),
                        isSelected = selected.contains(it),
                        onTap = { onTap(it) },
                        onLongPress = { onLongPress(it) },
                        showCategory = false,
                    )
                }
            )
        }
    }
}
