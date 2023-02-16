package com.sdk.domain.repository

import com.sdk.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface PersistenceRepository  {
    fun saveUserIdAndToken(token: String, userId: Int)

    suspend fun saveUser(userEntity: UserEntity)
    fun getUser(id: Int): Flow<UserEntity?>
    suspend fun deleteUser(userEntity: UserEntity)
}