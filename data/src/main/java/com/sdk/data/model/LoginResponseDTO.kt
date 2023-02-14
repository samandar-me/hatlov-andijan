package com.sdk.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    val success: Boolean,
    val token: Token,
    val user: LoginUser
)
data class Token(
    val refresh: String,
    val access: String
)
data class LoginUser(
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    val username: String,
    @SerializedName("is_authenticated")
    val isAuthenticated: Boolean,
    val role: Role,
    @SerializedName("mahalla")
    val neighborhood: Neighborhood
)
data class Role(
    val id: Int,
    val name: String
)
data class Neighborhood(
    val id: Int,
    val name: String
)