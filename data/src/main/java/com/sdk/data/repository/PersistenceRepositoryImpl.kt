package com.sdk.data.repository

import com.sdk.data.database.UserDao
import com.sdk.data.manager.SharedPrefManager
import com.sdk.domain.model.UserEntity
import com.sdk.domain.repository.PersistenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersistenceRepositoryImpl @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val dao: UserDao
): PersistenceRepository  {
    override fun saveUserIdAndToken(token: String, userId: Int) {
        sharedPrefManager.saveToken(token)
        sharedPrefManager.saveUserId(userId)
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