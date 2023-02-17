package com.sdk.hatlovandijon.ui.bottom.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddData(
    val address: String,
    val ownerHomeName: String,
    val ownerHomeYear: Int,
    val ownerHomeGender: Int,
    val ownerHomePhone: String,
    val ownerAsSpeaker: Int,
    val speakerName: String,
    val speakerYear: String,
    val speakerGender: String,
    val speakerPhone: String,
): Parcelable