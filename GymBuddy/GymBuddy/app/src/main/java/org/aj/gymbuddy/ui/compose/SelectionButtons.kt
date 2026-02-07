package org.aj.gymbuddy.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SelectionButtons(
    onAdd: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CancelButton(
            onClick = onCancel,
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )

        AddButton(
            onClick = onAdd,
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
    }
}
