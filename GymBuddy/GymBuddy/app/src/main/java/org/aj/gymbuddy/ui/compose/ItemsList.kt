package org.aj.gymbuddy.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemsList(
    items: List<String>,
    onDelete: (String) -> Unit,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(items) { item ->
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .animateItem()
                    .clickable(onClick = { onClick(item) })
            ) {
                SwipeToDelete(
                    key = item,
                    onDelete = { onDelete(item) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
