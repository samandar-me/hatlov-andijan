package com.sdk.hatlovandijon.ui.bottom.add

import android.os.Parcelable
import com.sdk.domain.model.detail.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddData(
    val id: Int = 0,
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
    val problemContent: String = "",
    val comment: String = "",
    val oldImages: List<Image> = emptyList()
): Parcelable