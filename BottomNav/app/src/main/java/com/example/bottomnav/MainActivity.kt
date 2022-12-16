package com.example.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnav.ui.theme.BottomNavTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BottomNavTheme {

				val navController = rememberNavController()
				Scaffold(bottomBar = {
					BottomNavigationBar(
						items = listOf(
							BottomNavItem(
								name = "Home",
								route = "home",
								icon = Icons.Default.Home
							),
							BottomNavItem(
								name = "Message",
								route = "message",
								icon = Icons.Default.Notifications,
								badgerCount = 28
							),
							BottomNavItem(
								name = "Settings",
								route = "settings",
								icon = Icons.Default.Settings,
								badgerCount = 281
							)),
						navController = navController, onItemClick = { item ->
							navController.navigate(item.route)
						})
				}) {
					Navigation(navController = navController)
				}
			}
		}
	}
}

@Composable
fun BottomNavigationBar(items : List<BottomNavItem>,
                        modifier : Modifier = Modifier,
                        navController : NavHostController,
                        onItemClick : (BottomNavItem) -> Unit) {

	val backStackEntry = navController.currentBackStackEntryAsState()

	BottomNavigation(
		modifier = modifier.fillMaxWidth(),
		backgroundColor = Color.DarkGray,
		elevation = 5.dp
	) {
		items.forEach { item ->
			val selected = item.route == backStackEntry.value?.destination?.route
			BottomNavigationItem(selected = selected,
			                     onClick = {
				                     onItemClick(item)
			                     },
			                     selectedContentColor = Color.Green,
			                     unselectedContentColor = Color.Gray,
			                     icon = {
				                     Column(horizontalAlignment = CenterHorizontally) {
					                     if (item.badgerCount > 0) {
						                     BadgedBox(
							                     badge = {
								                     Badge {
									                     Text(text = item.badgerCount.toString())
								                     }
							                     }
						                     ) {
							                     Icon(imageVector = item.icon, contentDescription = item.name)
						                     }
					                     } else {
						                     Icon(imageVector = item.icon, contentDescription = item.name)
					                     }
					                     if (selected) {
						                     Text(text = item.name,
						                          textAlign = TextAlign.Center,
						                          fontSize = 10.sp)
					                     }
				                     }
			                     }


			)
		}
	}
}

@Composable
fun Navigation(navController : NavHostController,
               modifier : Modifier = Modifier) {
	NavHost(navController = navController, startDestination = "home") {

		composable(route = "home") {
			HomeScreen(modifier = modifier)
		}

		composable(route = "message") {
			MsgScreen(modifier = modifier)
		}

		composable(route = "settings") {
			SettingScreen(modifier = modifier)
		}
	}
}

@Composable
fun HomeScreen(modifier : Modifier) {
	Box(modifier = modifier.fillMaxSize(),
	    contentAlignment = Alignment.Center) {

		Text(text = "Home Screen")
	}
}

@Composable
fun MsgScreen(modifier : Modifier) {
	Box(modifier = modifier.fillMaxSize(),
	    contentAlignment = Alignment.Center) {

		Text(text = "Message Screen")
	}
}

@Composable
fun SettingScreen(modifier : Modifier) {
	Box(modifier = modifier.fillMaxSize(),
	    contentAlignment = Alignment.Center) {

		Text(text = "Setting Screen")
	}
}

