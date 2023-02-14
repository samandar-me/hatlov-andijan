package com.sdk.domain.repository

import com.sdk.domain.model.LoginResponse
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(userName: String, password: String): Flow<Status<LoginResponse>>
}