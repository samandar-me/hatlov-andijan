package com.sdk.hatlovandijon.util

object Constants {
    const val BASE_URL = ""
    const val IMAGE_END = ""
    const val TAG = "@@@"
}
fun String.splitText(): String {
    return this.replace("\"", "")
}