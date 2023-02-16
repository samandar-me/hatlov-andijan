package com.sdk.data.model

import com.google.gson.annotations.SerializedName

data class VariablesResponse(
    val success: Boolean,
    val data: Data
)
data class Data(
    val status: List<Status>,
    @SerializedName("jins")
    val gender: List<Status>
)
data class Status(
    val id: Int,
    val name: String
)