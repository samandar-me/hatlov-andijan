package com.sdk.hatlovandijon.ui.splash

sealed class SplashState {
    object Loading: SplashState()
    data class IsLogin(val isLogin: Boolean): SplashState()
}