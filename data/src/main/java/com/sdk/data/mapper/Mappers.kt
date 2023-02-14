package com.sdk.data.mapper

import com.sdk.data.model.LoginResponseDTO
import com.sdk.domain.model.LoginResponse

fun LoginResponseDTO.toLogin(): LoginResponse {
    return LoginResponse(
        refresh = token.refresh,
        access = token.access,
        userId = user.id,
        userName = user.username,
        fullName = user.fullName,
        isAuthed = user.isAuthenticated,
        roleName = user.role.name,
        roleId = user.role.id,
        neiId = user.neighborhood.id,
        neiName = user.neighborhood.name
    )
}