package com.example.nurrgo.data.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.HttpTimeout

const val SERVER_URL = "http://192.168.1.110:8080" // Asegúrate de que esta IP sea accesible desde tu emulador/dispositivo

object HttpClientProvider {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000 // 15 segundos
        }
    }
}
