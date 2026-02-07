package org.aj.gymbuddy.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.aj.gymbuddy.ui.compose.NavigationBottomBar
import org.aj.gymbuddy.ui.compose.TopBar
import androidx.compose.runtime.getValue
import kotlinx.coroutines.launch
import org.aj.gymbuddy.db.SettingsConstants
import org.aj.gymbuddy.db.SettingsEntity
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    navController: NavHostController,
    model: SettingsModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    var settingName by remember { mutableStateOf("Enable recommended workouts") }
    var isRecommendedWorkoutsEnabled by remember { mutableStateOf(false) }
    val isDarkThemeEnabled by model.isDarkThemeEnabledFlow.collectAsState()

    LaunchedEffect(Unit) {
        val name = model.getNameValueById(SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS)
        val value = model.getSettingValueById(SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS)
        settingName = name ?: settingName
        isRecommendedWorkoutsEnabled = value == true
    }

    //var isDarkThemeEnabled by remember { mutableStateOf(false) }
    //var isPrivateProfile by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(navController)
        },
        bottomBar = {
            NavigationBottomBar(navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Profile Settings Section
            /*Text(
                text = "Profile Settings",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsOptionRow("Profile Picture", onClick = { /* Handle profile picture click */ })
            SettingsOptionRow("Name", onClick = { /* Handle name update */ })
            SettingsOptionRow("Email", onClick = { /* Handle email update */ })

            Spacer(modifier = Modifier.height(16.dp))*/

            // Notification Settings Section
            // Workouts Settings Section
            Text(
                text = "Workouts Settings",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = settingName,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isRecommendedWorkoutsEnabled,
                        onCheckedChange = { isChecked ->
                            isRecommendedWorkoutsEnabled = isChecked
                            coroutineScope.launch {
                                model.upsertSetting(
                                    SettingsEntity(
                                        id = SettingsConstants.ENABLE_RECOMMENDED_WORKOUTS,
                                        name = settingName,
                                        value = isChecked
                                    )
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Theme Settings",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Mode",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isDarkThemeEnabled,
                        onCheckedChange = { isChecked ->
                            model.toggleDarkMode(isChecked)
                        }
                    )
                }
            }


            /*Spacer(modifier = Modifier.height(16.dp))

            // Theme Settings Section
            Text(
                text = "Theme Settings",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsSwitchRow("Dark Theme", isChecked = isDarkThemeEnabled) {
                isDarkThemeEnabled = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Privacy Settings Section
            Text(
                text = "Privacy Settings",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsSwitchRow("Private Profile", isChecked = isPrivateProfile) {
                isPrivateProfile = it
            }*/
        }
    }
}

/*
@Composable
fun SettingsOptionRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Navigate",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
*/

@Composable
fun SettingsSwitchRow(title: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
        )
    }
}
