package com.sdk.domain.use_case.base

import com.sdk.domain.use_case.login.LoginUseCase
import com.sdk.domain.use_case.main.*

data class AllUseCases(
    val loginUseCase: LoginUseCase,
    val saveTokenUseCase: SaveTokenUseCase,
    val getVariableUseCase: GetVariableUseCase,
    val getAppealsUseCase: GetAppealsUseCase,
    val saveUserUseCase: SaveUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val getDetailImagesUseCase: GetDetailImagesUseCase,
    val addAppealUseCase: AddAppealUseCase,
    val searchAppealTypeUseCase: SearchAppealTypeUseCase,
    val searchAppealDashboardUseCase: SearchAppealDashboardUseCase,
    val updateAppealUseCase: UpdateAppealUseCase
)