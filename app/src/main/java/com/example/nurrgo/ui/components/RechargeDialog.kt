package com.example.nurrgo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.nurrgo.ui.theme.AppSpacing

@Composable
fun RechargeDialog(
    onDismiss: () -> Unit,
    onConfirm: (Double) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(AppSpacing.md)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Recargar saldo",
                    style = MaterialTheme.typography.headlineSmall
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = amount,
                    onValueChange = { 
                        amount = it
                        isError = false
                    },
                    label = { Text("Monto") },
                    prefix = { Text("$") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    isError = isError,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                
                if (isError) {
                    Text(
                        text = "Por favor ingrese un monto válido",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Button(
                        onClick = {
                            try {
                                val amountValue = amount.toDouble()
                                if (amountValue <= 0) {
                                    isError = true
                                } else {
                                    onConfirm(amountValue)
                                }
                            } catch (e: NumberFormatException) {
                                isError = true
                            }
                        }
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        }
    }
}