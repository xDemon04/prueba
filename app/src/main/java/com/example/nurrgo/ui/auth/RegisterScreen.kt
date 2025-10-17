package com.example.nurrgo.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nurrgo.SessionManager
import com.example.nurrgo.data.models.CsrfTokenResponse
import com.example.nurrgo.data.models.LoginRequest // Reutilizaremos este para el registro si la API lo permite, o crearemos uno nuevo
import com.example.nurrgo.data.models.LoginResponse // Reutilizaremos este para el registro si la API lo permite, o crearemos uno nuevo
import com.example.nurrgo.data.network.HttpClientProvider
import com.example.nurrgo.data.network.SERVER_URL
import com.example.nurrgo.ui.components.AppButton
import com.example.nurrgo.ui.components.AppCard
import com.example.nurrgo.ui.components.AppTextField
import com.example.nurrgo.ui.components.GradientBackground
import com.example.nurrgo.ui.theme.AppColors
import com.example.nurrgo.ui.theme.AppSpacing
import com.example.nurrgo.ui.theme.AppTypography
import com.example.nurrgo.ui.theme.NurrgoTheme
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Composable
fun RegisterScreen(
    sessionManager: SessionManager,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registerResult by remember { mutableStateOf<String?>(null) }
    var csrfToken by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Fetch CSRF token when screen loads
    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val response: HttpResponse = HttpClientProvider.client.get("$SERVER_URL/api/csrf-token") {
                    headers {
                        append(HttpHeaders.Accept, ContentType.Application.Json.toString())
                    }
                }
                if (response.status == HttpStatusCode.OK) {
                    val csrfResponse: CsrfTokenResponse = response.body()
                    withContext(Dispatchers.Main) {
                        csrfToken = csrfResponse.csrf_token
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        registerResult = "Error al obtener CSRF token: ${response.status.value}"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    registerResult = "Error al obtener CSRF token: ${e.message}"
                }
            }
        }
    }

    fun register() {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            registerResult = "Por favor, completa todos los campos"
            return
        }
        if (password != confirmPassword) {
            registerResult = "Las contraseñas no coinciden"
            return
        }

        isLoading = true
        registerResult = null

        coroutineScope.launch(Dispatchers.IO) {
            try {
                // Asumiendo que la API de registro usa un formato similar al de login
                val response: HttpResponse = HttpClientProvider.client.submitForm(
                    url = "$SERVER_URL/register/", // Ajusta esta URL si es diferente
                    formParameters = parameters {
                        append("csrf_token", csrfToken ?: "")
                        append("request", Json.encodeToString(LoginRequest(email, password))) // Usando LoginRequest como ejemplo
                    }
                )

                withContext(Dispatchers.Main) {
                    if (response.status == HttpStatusCode.OK || response.status == HttpStatusCode.Created) {
                        val registerResponse: LoginResponse = response.body() // Asumiendo que la respuesta es similar a LoginResponse
                        sessionManager.saveToken(registerResponse.access_token, registerResponse.usuario_id, registerResponse.tipo_usuario ?: "usuario")
                        registerResult = registerResponse.message
                        onRegisterSuccess()
                    } else if (response.status == HttpStatusCode.BadRequest || response.status == HttpStatusCode.UnprocessableEntity) {
                        val errorBody = response.bodyAsText()
                        try {
                            val errorJson = Json.parseToJsonElement(errorBody).jsonObject
                            val errorMessage = errorJson["detail"]?.jsonPrimitive?.content
                                ?: errorJson["error"]?.jsonPrimitive?.content
                                ?: "Error en el registro"
                            registerResult = errorMessage
                        } catch (e: Exception) {
                            registerResult = "Error al procesar la respuesta: ${e.message}"
                        }
                    } else {
                        registerResult = "Error del servidor: ${response.status.value}"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    registerResult = "Error al conectar con el servidor: ${e.message}"
                }
            } finally {
                withContext(Dispatchers.Main) {
                    isLoading = false
                }
            }
        }
    }

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppSpacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Placeholder for the logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "[LOGO]", style = AppTypography.headlineLarge)
            }

            Spacer(modifier = Modifier.height(AppSpacing.xl))

            // Welcome Card
            AppCard(
                modifier = Modifier.fillMaxWidth(),
                containerColor = AppColors.Surface.copy(alpha = 0.95f)
            ) {
                Column(
                    modifier = Modifier.padding(AppSpacing.xl),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Crear Cuenta",
                        style = AppTypography.headlineMedium,
                        color = AppColors.OnSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.sm))

                    Text(
                        text = "Regístrate para empezar",
                        style = AppTypography.bodyLarge,
                        color = AppColors.OnSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.xl))

                    // Email Field
                    AppTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Correo electrónico",
                        placeholder = "tu@email.com",
                        leadingIcon = Icons.Default.Email,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isError = registerResult != null && email.isBlank(),
                        errorMessage = if (registerResult != null && email.isBlank()) "El correo es requerido" else null
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.md))

                    // Password Field
                    AppTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Contraseña",
                        placeholder = "Tu contraseña",
                        leadingIcon = Icons.Default.Lock,
                        trailingIcon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        onTrailingIconClick = { showPassword = !showPassword },
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = registerResult != null && password.isBlank(),
                        errorMessage = if (registerResult != null && password.isBlank()) "La contraseña es requerida" else null
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.md))

                    // Confirm Password Field
                    AppTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = "Confirmar Contraseña",
                        placeholder = "Repite tu contraseña",
                        leadingIcon = Icons.Default.Lock,
                        trailingIcon = if (showConfirmPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        onTrailingIconClick = { showConfirmPassword = !showConfirmPassword },
                        visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = registerResult != null && confirmPassword.isBlank(),
                        errorMessage = if (registerResult != null && confirmPassword.isBlank()) "Confirma tu contraseña" else null
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.lg))

                    // Register Button
                    AppButton(
                        text = "Registrarse",
                        onClick = { register() },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = csrfToken != null && !isLoading,
                        isLoading = isLoading,
                        icon = Icons.Default.Lock
                    )

                    // Error/Success Message
                    registerResult?.let { result ->
                        Spacer(modifier = Modifier.height(AppSpacing.md))
                        Text(
                            text = result,
                            style = AppTypography.bodyMedium,
                            color = if (result.contains("exitoso")) AppColors.Primary else AppColors.Error, // Assuming "exitoso" for success
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(AppSpacing.lg))

                    // Login Link
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "¿Ya tienes cuenta? ",
                            style = AppTypography.bodyMedium,
                            color = AppColors.OnPrimary.copy(alpha = 0.8f)
                        )
                        TextButton(
                            onClick = onLoginClick,
                            colors = ButtonDefaults.textButtonColors(contentColor = AppColors.OnPrimary)
                        ) {
                            Text(
                                "Inicia sesión aquí",
                                style = AppTypography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    NurrgoTheme {
        val context = LocalContext.current
        val sessionManager = remember { SessionManager(context) }
        RegisterScreen(sessionManager = sessionManager, onRegisterSuccess = {}, onLoginClick = {})
    }
}
