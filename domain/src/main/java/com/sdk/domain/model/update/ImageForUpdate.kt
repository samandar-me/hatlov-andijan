package com.sdk.domain.model.update

import java.io.File

data class ImageForUpdate(
    val oldId: Int,
    val newImage: File
)
