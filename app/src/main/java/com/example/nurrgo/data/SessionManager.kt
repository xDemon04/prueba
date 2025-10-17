package com.example.nurrgo.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("NurrgoPrefs", Context.MODE_PRIVATE)
    
    var token: String?
        get() = sharedPreferences.getString("token", null)
        set(value) {
            sharedPreferences.edit().putString("token", value).apply()
        }
    
    var userId: String?
        get() = sharedPreferences.getString("userId", null)
        set(value) {
            sharedPreferences.edit().putString("userId", value).apply()
        }
    
    var userRole: String?
        get() = sharedPreferences.getString("userRole", null)
        set(value) {
            sharedPreferences.edit().putString("userRole", value).apply()
        }
    
    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}