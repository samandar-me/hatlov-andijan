package com.sdk.domain.use_case.base

import com.sdk.domain.use_case.login.LoginUseCase

data class AllUseCases(
    val loginUseCase: LoginUseCase
)