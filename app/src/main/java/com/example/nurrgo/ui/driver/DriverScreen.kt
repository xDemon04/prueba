package com.example.nurrgo.ui.driver

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DriverScreen(onBusInfoClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Driver Mode", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Start Route")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBusInfoClick, modifier = Modifier.fillMaxWidth()) {
            Text("View Bus Information")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) {
            Text("End Route")
        }
    }
}
