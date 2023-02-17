package com.sdk.domain.model.upload

import java.io.File

data class AddAppealRequest(
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
    val images: List<File>
)