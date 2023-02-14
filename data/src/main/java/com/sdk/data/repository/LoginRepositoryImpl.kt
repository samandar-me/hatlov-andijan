package com.sdk.data.repository

import com.sdk.data.mapper.toLogin
import com.sdk.data.network.login.LoginService
import com.sdk.domain.model.LoginResponse
import com.sdk.domain.repository.LoginRepository
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): LoginRepository {
    override suspend fun login(
        userName: String,
        password: String,
    ): Flow<Status<LoginResponse>> = flow {
        emit(Status.Loading)
        try {
             val response = loginService.login(userName, password)
            if (response.body()?.success!!) {
                response.body()?.let {
                    emit(Status.Success(it.toLogin()))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Status.Error(e.message.toString()))
        }
    }
}