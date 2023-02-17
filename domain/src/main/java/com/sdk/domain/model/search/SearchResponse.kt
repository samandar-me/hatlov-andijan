package com.sdk.domain.model.search

data class SearchResponse(
    val `data`: List<SearchData>,
    val success: Boolean
)