package com.sdk.hatlovandijon.util

object Constants {
    const val BASE_URL = "https://xatlov.andijon.uz/"
    const val IMAGE_END = "media/murojaat-image/"
    const val TAG = "@@@"
}
fun String.splitText(): String {
    return this.replace("\"", "")
}