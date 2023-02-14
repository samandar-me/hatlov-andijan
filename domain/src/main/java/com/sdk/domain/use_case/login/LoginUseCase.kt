package com.sdk.domain.use_case.login

import com.sdk.domain.model.LoginData
import com.sdk.domain.model.LoginResponse
import com.sdk.domain.repository.LoginRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias LoginBaseUseCase = BaseUseCase<LoginData, Flow<Status<LoginResponse>>>

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
):  LoginBaseUseCase {
    override suspend fun invoke(params: LoginData): Flow<Status<LoginResponse>> {
        return loginRepository.login(params.userName, params.password)
    }
}