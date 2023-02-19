package com.sdk.domain.model.filter

data class Pagination(
    val current_page: Int,
    val items_count: Int,
    val next_page: Any,
    val number_of_object: Int,
    val pages_count: Int,
    val pervios_page: Any
)