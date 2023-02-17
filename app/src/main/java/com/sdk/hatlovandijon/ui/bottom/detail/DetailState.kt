package com.sdk.hatlovandijon.ui.bottom.detail

import com.sdk.domain.model.detail.DetailResponse

sealed class DetailState {
    object Loading: DetailState()
    data class Error(val message: String): DetailState()
    data class Success(val detailData: DetailResponse): DetailState()
}