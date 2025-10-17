package com.example.nurrgo.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.example.nurrgo.ui.components.AppButton
import com.example.nurrgo.ui.theme.AppColors
import com.example.nurrgo.ui.theme.AppShapes
import com.example.nurrgo.ui.theme.AppSpacing
import com.example.nurrgo.ui.theme.AppTypography

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val defaultLocation = LatLng(1.35, 103.87) // Ejemplo: Singapur
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = defaultLocation),
                title = "Ubicación Actual",
                snippet = "Tu posición en el mapa"
            )
        }

        // Floating Action Panel for QR Code
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = AppShapes.large.topStart, topEnd = AppShapes.large.topEnd))
                .background(MaterialTheme.colorScheme.surface)
                .padding(AppSpacing.md),
            shadowElevation = AppSpacing.sm // Añadir una sombra para que "flote"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.md),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Escanea para Pagar",
                    style = AppTypography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.OnSurface
                )
                Spacer(modifier = Modifier.height(AppSpacing.md))
                Card(
                    modifier = Modifier.size(220.dp), // Aumentar un poco el tamaño del QR
                    shape = AppShapes.medium,
                    elevation = CardDefaults.cardElevation(defaultElevation = AppSpacing.md),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "[CÓDIGO QR AQUÍ]",
                            textAlign = TextAlign.Center,
                            style = AppTypography.headlineLarge,
                            color = AppColors.OnSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(AppSpacing.md))
                Text(
                    "Muestra este código al conductor para validar tu viaje.",
                    style = AppTypography.bodyLarge,
                    color = AppColors.OnSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(AppSpacing.md))
                AppButton(
                    text = "Usar QR",
                    onClick = { /* TODO: Acción al escanear o usar QR */ },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
