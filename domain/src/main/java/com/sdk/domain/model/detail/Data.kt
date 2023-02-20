package com.sdk.domain.model.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Data(
    val added_date: String,
    val address: String,
    val deadline: String,
    val id: Int,
    val images: List<Image>,
    val izoh: String,
    val mahalla: String,
    val owner_as_speaker: Boolean,
    val owner_home_jinsi: String,
    val owner_home_name: String,
    val owner_home_phone: String,
    val owner_home_year: Int,
    val speaker_jinsi: String,
    val speaker_name: String?,
    val speaker_phone: String?,
    val speaker_year: Int?,
    val status: Status,
    val turi: Turi,
    val xodim: String
): Parcelable