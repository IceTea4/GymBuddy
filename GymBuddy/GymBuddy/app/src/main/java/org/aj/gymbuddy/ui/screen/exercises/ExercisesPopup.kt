package org.aj.gymbuddy.ui.screen.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.aj.gymbuddy.db.ExerciseCategory
import org.aj.gymbuddy.ui.compose.WebViewComponent

@Composable
fun ExercisesPopup(exercise: ExerciseCategory.Exercise, onDismiss: () -> Unit) {
    val url = exercise.demo.toString()
    val scroll = rememberScrollState()

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.95f),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        exercise.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center)
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close, contentDescription = "Close"
                        )
                    }
                }

                if (url != "") {
                    WebViewComponent(url)
                }

                Text(text = exercise.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .heightIn(min = 70.dp, max = 300.dp)
                        .verticalScroll(scroll),
                )
            }
        }
    }
}
