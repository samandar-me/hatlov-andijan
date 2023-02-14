package com.sdk.hatlovandijon.ui.auth

import com.sdk.domain.model.LoginData

sealed class LoginActivityEvent {
    data class OnLoginClicked(val loginData: LoginData): LoginActivityEvent()
}