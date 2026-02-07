package org.aj.gymbuddy.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import org.aj.gymbuddy.R
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    currentWeekName: String = "",
    showLeft: Boolean = false,
    showRight: Boolean = false,
    showExit: Boolean = false,
    onLeftArrowClick: () -> Unit = {},
    onRightArrowClick: () -> Unit = {},
    onExitClick: () -> Unit = {}
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val currentDate = remember {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MM - dd")
        val weekday = today.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())
        "${today.format(formatter)}, $weekday"
    }

    val nextWeek = remember {
        val nextDay = LocalDate.now().plusDays(7)
        val formatter = DateTimeFormatter.ofPattern("MM - dd")
        val weekday = nextDay.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())
        "Next week - $weekday, ${nextDay.format(formatter)}"
    }

    val previousWeek = remember {
        val previousDay = LocalDate.now().minusDays(7)
        val formatter = DateTimeFormatter.ofPattern("MM - dd")
        val weekday = previousDay.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())
        "Past week, $weekday, ${previousDay.format(formatter)}"
    }

    val title = when (currentRoute) {
        "home" -> currentWeekName
        "workouts" -> "Your Workout Plans"
        "settings" -> "Settings"
        "workout" -> "Create new Workout"
        "event" -> "Create new Event"
        "exercises" -> "Exercises"
        "exercise" -> "Add selected exercises to Plan"
        "next" -> nextWeek
        "previous" -> previousWeek
        "edit_workout/{workoutId}" -> "Workout editing"
        else -> ""
    }

    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dumbbellandname),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .height(48.dp)
                        .padding(bottom = 4.dp)
                )
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        },
        navigationIcon = {
            if (showLeft) {
                IconButton(onClick = onLeftArrowClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Left Arrow"
                    )
                }
            }
        },
        actions = {
            if (showRight) {
                IconButton(onClick = onRightArrowClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Right Arrow"
                    )
                }
            }
            else if (showExit){
                IconButton(onClick = onExitClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
