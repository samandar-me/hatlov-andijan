package com.sdk.hatlovandijon.ui.bottom.detail

sealed class DetailEvent {
    data class OnGetDetailData(val id: Int): DetailEvent()
}