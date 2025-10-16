package com.example.nurrgo

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    var token: String? by mutableStateOf(prefs.getString("auth_token", null))
        private set

    var usuarioId: Int? by mutableStateOf(prefs.getInt("usuario_id", -1).takeIf { it != -1 })
        private set

    var tipoUsuario: String? by mutableStateOf(prefs.getString("tipo_usuario", null))
        private set

    fun saveToken(token: String, usuarioId: Int, tipoUsuario: String) {
        prefs.edit().apply {
            putString("auth_token", token)
            putInt("usuario_id", usuarioId)
            putString("tipo_usuario", tipoUsuario)
            apply()
        }
        this.token = token
        this.usuarioId = usuarioId
        this.tipoUsuario = tipoUsuario
    }

    fun clearToken() {
        prefs.edit().apply {
            remove("auth_token")
            remove("usuario_id")
            remove("tipo_usuario")
            apply()
        }
        this.token = null
        this.usuarioId = null
        this.tipoUsuario = null
    }
}
