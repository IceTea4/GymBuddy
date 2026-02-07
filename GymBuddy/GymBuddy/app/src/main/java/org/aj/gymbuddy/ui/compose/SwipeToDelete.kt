package org.aj.gymbuddy.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxState.Companion.Saver
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import org.aj.gymbuddy.lang.then

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDelete(
    key: Any,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    val canSwipe = remember { mutableStateOf(false) }

    val swipeState = rememberSwipeBoxState(
        key = key,
        confirmValueChange = { value ->
            canSwipe.value.then {
                onDelete()
            }
        }
    )

    val backgroundColor = when {
        swipeState.targetValue == SwipeToDismissBoxValue.EndToStart ->
            MaterialTheme.colorScheme.error.copy(alpha = swipeState.progress)
        else -> Color.Transparent
    }

    canSwipe.value = swipeState.progress > 0.3f && swipeState.progress < 1.0f

    SwipeToDismissBox(
        state = swipeState,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {},
        modifier = modifier
            .background(color = backgroundColor),
        content = content
    )
}

@Composable
@ExperimentalMaterial3Api
private fun rememberSwipeBoxState(
    key: Any,
    confirmValueChange: (SwipeToDismissBoxValue) -> Boolean = { true },
    positionalThreshold: (totalDistance: Float) -> Float =
        SwipeToDismissBoxDefaults.positionalThreshold
): SwipeToDismissBoxState {
    val density = LocalDensity.current
    return rememberSaveable(
        key,
        saver = Saver(
            confirmValueChange = confirmValueChange,
            density = density,
            positionalThreshold = positionalThreshold
        )
    ) {
        SwipeToDismissBoxState(
            SwipeToDismissBoxValue.Settled,
            density,
            confirmValueChange,
            positionalThreshold
        )
    }
}
