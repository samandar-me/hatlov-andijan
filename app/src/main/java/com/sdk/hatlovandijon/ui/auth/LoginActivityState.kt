package com.sdk.hatlovandijon.ui.auth

import com.sdk.domain.model.LoginResponse

sealed class LoginActivityState {
    object Idle: LoginActivityState()
    object Loading: LoginActivityState()
    data class Error(val message: String): LoginActivityState()
    data class Success(val login: LoginResponse): LoginActivityState()
}