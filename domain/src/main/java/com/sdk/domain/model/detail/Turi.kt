package com.sdk.domain.model.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Turi(
    val color: String,
    val id: Int,
    val name: String
): Parcelable