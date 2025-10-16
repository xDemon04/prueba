package com.example.nurrgo.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val email: String, val password: String)

@Serializable
data class LoginResponse(
    val access_token: String,
    val usuario_id: Int,
    val message: String,
    val token_type: String,
    val tipo_usuario: String? = "usuario",
    val tiene_preguntas_seguridad: Boolean
)

@Serializable
data class CsrfTokenResponse(val csrf_token: String)
