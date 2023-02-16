package com.sdk.hatlovandijon.ui.bottom.main

import com.sdk.domain.model.appeal.AppealResponse

sealed class MainState {
    object Loading: MainState()
    data class Error(val message: String): MainState()
    data class SuccessAppeals(val data: AppealResponse): MainState()
}