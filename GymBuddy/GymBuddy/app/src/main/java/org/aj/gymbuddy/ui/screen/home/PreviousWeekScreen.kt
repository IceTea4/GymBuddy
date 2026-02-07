package org.aj.gymbuddy.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.aj.gymbuddy.ui.compose.NavigationBottomBar
import org.aj.gymbuddy.ui.compose.TopBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PreviousWeekScreen(navController: NavHostController) {
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d")
    val prevWeekDates = (0..7).map { LocalDate.now().minusDays(it.toLong()).format(formatter)}
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController,
                showRight = true,
                onRightArrowClick = {
                    navController.navigate("home")
                }
            )
        },
        bottomBar = {
            NavigationBottomBar(navController)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )
        {
            Text(
                text="Previous week (Today - 7 days)",
                modifier=Modifier.align(Alignment.TopStart).padding(top=16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                prevWeekDates.reversed().forEach { date ->
                    Text(date)
                }
            }
        }
    }
}
