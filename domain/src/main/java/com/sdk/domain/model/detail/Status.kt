package com.sdk.domain.model.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Status(
    val color: String,
    val name: String,
    val id: Int
): Parcelable