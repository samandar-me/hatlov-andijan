package com.sdk.hatlovandijon.ui.splash

sealed class SplashState {
    object Idle: SplashState()
    object UserAuthed: SplashState()
    object UserNotAuthed: SplashState()
    object NoInternet: SplashState()
}