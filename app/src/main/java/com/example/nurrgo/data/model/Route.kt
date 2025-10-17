package com.example.nurrgo.data.model

data class Route(
    val id: String = "",
    val name: String = "",
    val startPoint: String = "",
    val endPoint: String = "",
    val stops: List<String> = emptyList(),
    val schedule: List<String> = emptyList(),
    val driverId: String = ""
)