package com.sdk.data.manager

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("Shared",Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().apply {
            putString("token", token)
        }.apply()
    }
    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }
    fun saveUserId(id: Int) {
        sharedPreferences.edit()
            .putInt("userId", id)
            .apply()
    }
    fun getUserId(): Int {
        return sharedPreferences.getInt("userId", 0)
    }
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}