package com.example.nurrgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder.popUpTo
import com.example.nurrgo.data.SessionManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nurrgo.ui.auth.LoginScreen
import com.example.nurrgo.ui.auth.RegisterScreen
import com.example.nurrgo.ui.driver.BusInfoScreen
import com.example.nurrgo.ui.driver.DriverScreen
import com.example.nurrgo.ui.main.MainScreen
import com.example.nurrgo.ui.theme.NurrgoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NurrgoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val sessionManager = remember { SessionManager(context) }
                    AppNavigation(sessionManager = sessionManager)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(sessionManager: SessionManager) {
    val navController = rememberNavController()
    val startDestination = if (sessionManager.token != null) "main" else "login"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                sessionManager = sessionManager,
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(

                sessionManager = sessionManager,
                onRegisterSuccess = {
                    navController.navigate("main") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate("login") }
            )
        }
        composable("main") {
            MainScreen(onNavigateToDriver = { navController.navigate("driver") })
        }
        composable("driver") {
            DriverScreen(onBusInfoClick = { navController.navigate("bus_info") })
        }
        composable("bus_info") {
            BusInfoScreen()
        }
    }
}
