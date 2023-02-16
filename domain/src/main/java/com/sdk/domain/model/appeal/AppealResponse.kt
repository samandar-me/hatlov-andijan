package com.sdk.domain.model.appeal

import com.google.gson.annotations.SerializedName

data class AppealResponse(
    @SerializedName("data")
    val `data`: DataAppealResponse,
    val success: Boolean
): java.io.Serializable