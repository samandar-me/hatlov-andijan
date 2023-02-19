package com.sdk.domain.repository

import com.sdk.domain.model.LoginData
import com.sdk.domain.model.UserEntity
import com.sdk.domain.model.VariableStatus
import kotlinx.coroutines.flow.Flow

interface PersistenceRepository  {
    suspend fun saveUserData(loginData: LoginData, token: String, userId: Int)

    suspend fun saveUser(userEntity: UserEntity)
    fun getUser(id: Int): Flow<UserEntity?>
    suspend fun deleteUser(userEntity: UserEntity)
}