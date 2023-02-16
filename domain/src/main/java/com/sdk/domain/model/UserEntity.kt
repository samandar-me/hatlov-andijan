package com.sdk.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val refreshToken: String,
    val accessToken: String,
    val userId: Int,
    val userName: String,
    val fullName: String,
    val roleName: String,
    val roleId: Int,
    val neiId: Int,
    val neiName: String
)