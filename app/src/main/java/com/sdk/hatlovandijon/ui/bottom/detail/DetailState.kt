package com.sdk.hatlovandijon.ui.bottom.detail

import com.sdk.domain.model.detail.DetailImage

sealed class DetailState {
    object Loading: DetailState()
    data class Error(val message: String): DetailState()
    data class Success(val images: List<DetailImage>): DetailState()
}