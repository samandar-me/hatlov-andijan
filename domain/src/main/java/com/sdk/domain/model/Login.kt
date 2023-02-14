package com.sdk.domain.model

data class LoginData(
    val userName: String,
    val password: String
)

data class LoginResponse(
    val refresh: String,
    val access: String,
    val userId: Int,
    val userName: String,
    val fullName: String,
    val isAuthed: Boolean,
    val roleName: String,
    val roleId: Int,
    val neiId: Int,
    val neiName: String
)