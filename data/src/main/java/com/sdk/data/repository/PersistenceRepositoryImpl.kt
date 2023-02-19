package com.sdk.data.repository

import com.sdk.data.database.UserDao
import com.sdk.data.manager.DataStoreManager
import com.sdk.domain.model.LoginData
import com.sdk.domain.model.UserEntity
import com.sdk.domain.model.VariableStatus
import com.sdk.domain.repository.PersistenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersistenceRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val dao: UserDao
): PersistenceRepository  {
    override suspend fun saveUserData(loginData: LoginData, token: String, userId: Int) {
        dataStoreManager.saveToken(token)
        dataStoreManager.saveUserId(userId)
        dataStoreManager.saveUser(loginData)
    }

    override suspend fun saveUser(userEntity: UserEntity) {
        dao.saveUser(userEntity)
    }

    override fun getUser(id: Int): Flow<UserEntity?> {
        return dao.getUser(id)
    }

    override suspend fun deleteUser(userEntity: UserEntity) {
        dao.deleteUser(userEntity)
    }
}