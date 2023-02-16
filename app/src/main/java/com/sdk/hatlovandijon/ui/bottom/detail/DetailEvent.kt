package com.sdk.hatlovandijon.ui.bottom.detail

sealed class DetailEvent {
    data class OnGetDetailImages(val id: Int): DetailEvent()
}