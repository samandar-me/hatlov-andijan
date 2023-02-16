package com.sdk.domain.model

data class DataVariable(
    val status: List<DomainStatus>,
    val gender: List<DomainStatus>
)
data class DomainStatus(
    val id: Int,
    val name: String
)
