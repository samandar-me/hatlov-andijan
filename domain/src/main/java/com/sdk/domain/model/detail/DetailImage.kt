package com.sdk.domain.model.detail

import com.google.gson.annotations.SerializedName

data class DetailImageResponse(
    @SerializedName("data")
    val detailData: Data
)
data class Data(
    val images: List<DetailImage>
)
data class DetailImage(
    val id: Int,
    val image: String?
)