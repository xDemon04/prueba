package com.example.nurrgo.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class HistoryItem(val id: Int, val title: String, val date: String, val amount: String)

val sampleHistory = listOf(
    HistoryItem(1, "Viaje a Centro", "15 Oct 2025", "$2.50"),
    HistoryItem(2, "Viaje a Norte", "14 Oct 2025", "$3.00"),
    HistoryItem(3, "Recarga de Saldo", "13 Oct 2025", "+$10.00"),
    HistoryItem(4, "Viaje a Sur", "12 Oct 2025", "$2.75"),
)

@Composable
fun HistoryScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(sampleHistory) { item ->
            HistoryListItem(item = item)
        }
    }
}

@Composable
fun HistoryListItem(item: HistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Text(text = item.date, style = MaterialTheme.typography.bodySmall)
            }
            Text(text = item.amount)
        }
    }
}
