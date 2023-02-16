package com.sdk.domain.model.appeal

data class Mahalla(
    val id: Int,
    val is_active: Boolean,
    val name: String,
    val sektor: String,
    val tuman: String
): java.io.Serializable