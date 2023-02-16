package com.sdk.data.mapper

import com.sdk.data.model.LoginResponseDTO
import com.sdk.data.model.Status
import com.sdk.data.model.VariablesResponse
import com.sdk.domain.model.DataVariable
import com.sdk.domain.model.DomainStatus
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
fun VariablesResponse.toVariableData(): DataVariable {
    return DataVariable(
        status = data.status.map { it.toDomainStatus() },
        gender = data.gender.map { it.toDomainStatus() }
    )
}
fun Status.toDomainStatus(): DomainStatus {
    return DomainStatus(
        id = id,
        name = name
    )
}