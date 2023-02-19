package com.sdk.hatlovandijon.ui.bottom.main

sealed class MainEvent {
    object OnGetAppeals: MainEvent()
    data class OnSearchAppeal(val query: String): MainEvent()
}
