package com.example.nurrgo.data.repository

import com.example.nurrgo.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepository {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: Flow<User?> = _currentUser.asStateFlow()

    suspend fun login(email: String, password: String): Result<User> {
        // Simulación de login exitoso
        val user = User(
            id = "user_id_simulado",
            name = "Usuario Simulado",
            email = email,
            role = "user"
        )
        _currentUser.value = user
        return Result.success(user)
    }

    suspend fun register(name: String, email: String, password: String): Result<User> {
        // Simulación de registro exitoso
        val user = User(
            id = "user_id_simulado",
            name = name,
            email = email,
            role = "user"
        )
        _currentUser.value = user
        return Result.success(user)
    }

    fun logout() {
        _currentUser.value = null
    }
}