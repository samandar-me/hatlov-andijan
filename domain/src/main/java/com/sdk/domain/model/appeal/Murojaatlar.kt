package com.sdk.domain.model.appeal

data class Murojaatlar(
    val added_date: String,
    val address: String,
    val deadline: String,
    val id: Int,
    val izoh: String,
    val mahalla: String,
    val owner_as_speaker: Boolean,
    val owner_home_jinsi: String,
    val owner_home_name: String,
    val owner_home_phone: String,
    val owner_home_year: Int,
    val speaker_jinsi: String,
    val speaker_name: Any? = "",
    val speaker_phone: Any? = "",
    val speaker_year: Any? = 0,
    val status: Status,
    val turi: Turi,
    val xodim: String
): java.io.Serializable