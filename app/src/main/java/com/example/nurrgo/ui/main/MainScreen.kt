package com.example.nurrgo.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.nurrgo.ui.components.RechargeDialog // Import the RechargeDialog

sealed class Screen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object History : Screen("history", "History", Icons.Default.List)
    object Profile : Screen("profile", "Profile", Icons.Default.AccountCircle)
}

val items = listOf(
    Screen.Home,
    Screen.History,
    Screen.Profile,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToDriver: () -> Unit) {
    val navController = rememberNavController()
    var showRechargeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nurrgo") },
                actions = {
                    // Placeholder for user credits
                    Text(
                        text = "$10.00", // Replace with actual credits from ViewModel
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    IconButton(onClick = { showRechargeDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Recargar Créditos")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.History.route) { HistoryScreen() }
            composable(Screen.Profile.route) { ProfileScreen(onNavigateToDriver) }
        }

        if (showRechargeDialog) {
        RechargeDialog(
            onDismiss = { showRechargeDialog = false },
            onConfirm = { amount -> 
                // Aquí iría la lógica para procesar la recarga
                showRechargeDialog = false
            }
        )
    }
    }
}
