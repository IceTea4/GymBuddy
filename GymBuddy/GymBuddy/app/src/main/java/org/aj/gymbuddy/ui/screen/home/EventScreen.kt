package org.aj.gymbuddy.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FabPosition
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.aj.gymbuddy.ui.compose.FloatingSave
import org.aj.gymbuddy.ui.compose.TopBar

@Composable
fun EventScreen(
    navController: NavHostController
) {
    var eventName by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(navController)
        },
        floatingActionButton = {
            FloatingSave() {
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = eventName,
                onValueChange = {
                    eventName = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text("Event name")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Beginning"
            )
        }
    }
}
