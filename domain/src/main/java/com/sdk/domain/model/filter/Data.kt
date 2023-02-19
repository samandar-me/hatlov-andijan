package com.sdk.domain.model.filter

import com.sdk.domain.model.appeal.Murojaatlar

data class Data(
    val pagination: Pagination,
    val result: List<Murojaatlar>
)