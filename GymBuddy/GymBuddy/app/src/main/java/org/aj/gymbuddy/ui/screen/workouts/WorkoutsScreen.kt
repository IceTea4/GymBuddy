package org.aj.gymbuddy.ui.screen.workouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.aj.gymbuddy.db.RecommendedWorkouts
import org.aj.gymbuddy.db.SettingsConstants
import org.aj.gymbuddy.ui.compose.DropdownSection
import org.aj.gymbuddy.ui.compose.FloatingAdd
import org.aj.gymbuddy.ui.compose.NavigationBottomBar
import org.aj.gymbuddy.ui.compose.SwipeToDelete
import org.aj.gymbuddy.ui.compose.TopBar
import org.aj.gymbuddy.ui.screen.settings.SettingsModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WorkoutsScreen(
    navController: NavHostController,
    workoutsModel: WorkoutsModel = koinViewModel(),
    settingsModel: SettingsModel = koinViewModel()
) {
    val workoutsState by workoutsModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val showRecommended = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val value = settingsModel.getSettingValueById(SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS)
        showRecommended.value = value == true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController,
                showRight = true,
                onRightArrowClick = {
                    navController.navigate("exercises")
                }
            )
        },
        bottomBar = {
            NavigationBottomBar(navController)
        },
        floatingActionButton = {
            FloatingAdd {
                navController.navigate(route = "workout")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (workoutsState.workouts.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "You have no workout plans",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                items(workoutsState.workouts) { item ->
                    SwipeToDelete(
                        key = item.id,
                        onDelete = {
                            coroutineScope.launch {
                                workoutsModel.deleteWorkoutPlan(item)
                            }
                        }
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = {
                                navController.navigate("edit_workout/${item.id}")
                            },
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.name,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                }
            }

            if (showRecommended.value) {
                item {
                    DropdownSection(
                        title = "Recommended workout plans",
                        items = RecommendedWorkouts.entries,
                        isExpanded = isDropdownExpanded,
                        onExpandToggle = { isDropdownExpanded = !isDropdownExpanded },
                        titleExtractor = { it.title }
                    )
                }
            }
        }
    }
}
