package com.example.nurrgo.data.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "user",
    val profilePicture: String? = null
)