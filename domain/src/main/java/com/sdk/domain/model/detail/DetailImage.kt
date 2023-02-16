package com.sdk.domain.model.detail

data class DetailImageResponse(
    val images: List<DetailImage>
)
data class DetailImage(
    val id: Int,
    val image: String
)