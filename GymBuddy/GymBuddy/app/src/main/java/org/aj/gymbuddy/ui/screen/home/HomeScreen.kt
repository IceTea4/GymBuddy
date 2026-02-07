package org.aj.gymbuddy.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kizitonwose.calendar.core.yearMonth
import org.aj.gymbuddy.ui.compose.DayCardData
import org.aj.gymbuddy.ui.compose.FloatingAdd
import org.aj.gymbuddy.ui.compose.NavigationBottomBar
import org.aj.gymbuddy.ui.compose.TopBar
import org.aj.gymbuddy.ui.compose.EventData
import org.aj.gymbuddy.ui.compose.VerticalDayCard
import org.aj.gymbuddy.ui.compose.EventItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import org.aj.gymbuddy.ui.compose.DayEventsSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val today = LocalDate.now()
    var currentWeekStart by remember { mutableStateOf(today) }
    var currentYearMonth by remember { mutableStateOf(currentWeekStart.yearMonth) }
    var showCalendar by remember { mutableIntStateOf(0) }

    val sampleEvents = remember {
        mapOf(
            today to listOf(
                EventData("1", "Morning Workout", "Chest and triceps workout at the gym", "07:00 AM"),
                EventData("2", "Evening Run", "5km run in the park", "06:00 PM"),
                EventData("5", "Morning Workout", "Chest and triceps workout at the gym", "12:00 AM"),
                EventData("6", "Evening Run", "25km run in the park", "12:00 PM")
            ),
            today.plusDays(2) to listOf(
                EventData("3", "Yoga Class", "90 minute yoga session", "09:00 AM")
            ),
            today.plusDays(4) to listOf(
                EventData("4", "Swimming", "Swimming practice at pool", "04:30 PM")
            )
        )
    }

    val weekDays = remember(currentWeekStart) {
        (0..6).map { offset ->
            val date = currentWeekStart.plusDays(offset.toLong())
            DayCardData(date, sampleEvents[date] ?: emptyList())
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableStateOf<DayCardData?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val titleText = remember { mutableStateOf("Current week") }
    var isShown by remember { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController,
                currentWeekName = titleText.value,
                showLeft = isShown,
                showRight = true,
                onLeftArrowClick = {
                    if (showCalendar > -1) {
                        currentWeekStart = currentWeekStart.minusDays(7)
                        currentYearMonth = currentWeekStart.yearMonth
                        showCalendar--
                        if (showCalendar==-1)
                            isShown = false
                    }
                    titleText.value=if(showCalendar==0)
                        "Current week"
                    else
                        currentWeekStart.format(DateTimeFormatter.ofPattern("MMMM d")) + " - "+
                                currentWeekStart.plusDays(6).format(DateTimeFormatter.ofPattern("MMMM d"))
                },
                onRightArrowClick = {
                    currentWeekStart = currentWeekStart.plusDays(7)
                    currentYearMonth = currentWeekStart.yearMonth
                    showCalendar++
                    titleText.value=if(showCalendar==0)
                        "Current week"
                    else
                        currentWeekStart.format(DateTimeFormatter.ofPattern("MMMM d")) + " - "+
                                currentWeekStart.plusDays(6).format(DateTimeFormatter.ofPattern("MMMM d"))
                    if (showCalendar >= 0)
                        isShown = true
                }
            )
        },
        bottomBar = { NavigationBottomBar(navController) },
        floatingActionButton = {
            FloatingAdd { navController.navigate(route = "event") }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (showCalendar == 0) {
                    items(sampleEvents[today] ?: emptyList()) { event ->
                        EventItem(event)
                    }
                    items(weekDays.filter{!it.date.isEqual(today)}) { dayCard ->
                        VerticalDayCard(
                            dayCard = dayCard,
                            isToday = dayCard.date.isEqual(today),
                            onClick = {
                                selectedDay = dayCard
                                showBottomSheet = true
                            }
                        )
                    }
                } else {
                    items(weekDays) { dayCard ->
                        VerticalDayCard(
                            dayCard = dayCard,
                            isToday = dayCard.date.isEqual(today),
                            onClick = {
                                selectedDay = dayCard
                                showBottomSheet = true
                            }
                        )
                    }
                }
            }
        }
    }

    if (showBottomSheet && selectedDay != null) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = bottomSheetState
        ) {
            DayEventsSheet(selectedDay!!)
        }
    }
}
