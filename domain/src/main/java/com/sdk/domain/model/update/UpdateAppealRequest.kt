package com.sdk.domain.model.update

import com.sdk.domain.model.detail.Image
import java.io.File

data class UpdateAppealRequest(
    val id: Int,
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
    val type: Int,
    val comment: String,
    val oldImages: List<Image>,
    val newImages: List<File>
)