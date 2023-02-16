package com.sdk.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sdk.domain.model.LoginData
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "Prefs")

    companion object {
        private val TOKEN = stringPreferencesKey("token")
        private val USER_ID = intPreferencesKey("userId")
        private val USER_NAME = stringPreferencesKey("username")
        private val PASSWORD = stringPreferencesKey("password")
    }
    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }
    fun getToken() = context.dataStore.data.map {
        it[TOKEN]
    }
    suspend fun saveUserId(id: Int) {
        context.dataStore.edit {
            it[USER_ID] = id
        }
    }
    fun getUserId() = context.dataStore.data.map {
        it[USER_ID]
    }
    suspend fun saveUser(loginData: LoginData) {
        context.dataStore.edit {
            it[USER_NAME] = loginData.userName
            it[PASSWORD] = loginData.password
        }
    }
    fun getUser() = context.dataStore.data.map {
        LoginData(
            userName = it[USER_NAME] ?: "",
            password = it[PASSWORD] ?: ""
        )
    }
}