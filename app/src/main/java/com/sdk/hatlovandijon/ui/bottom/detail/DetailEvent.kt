package com.sdk.hatlovandijon.ui.bottom.detail

sealed class DetailEvent {
    data class OnGetDetailData(val id: Int): DetailEvent()
    data class OnDeleteImage(val id: Int): DetailEvent()
}