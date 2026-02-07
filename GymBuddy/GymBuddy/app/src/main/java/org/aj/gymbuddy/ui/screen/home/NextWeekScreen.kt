package org.aj.gymbuddy.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.aj.gymbuddy.ui.compose.FloatingAdd
import org.aj.gymbuddy.ui.compose.NavigationBottomBar
import org.aj.gymbuddy.ui.compose.TopBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.Box

@Composable
fun NextWeekScreen(navController: NavHostController) {
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d")
    val nextWeekDates = (0..7).map { LocalDate.now().plusDays(it.toLong()).format(formatter) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController,
                showLeft = true,
                onLeftArrowClick = {
                    navController.navigate("home")
                },
            )
        },
        bottomBar = {
            NavigationBottomBar(navController)
        },
        floatingActionButton = {
            FloatingAdd() {
                navController.navigate(route = "event")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )
        {
            Text(
                text="Next week (Today + 7 days)",
                modifier=Modifier.align(Alignment.TopStart).padding(top=16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                nextWeekDates.forEach { date ->
                    Text(date)
                }
            }
        }
    }
}
