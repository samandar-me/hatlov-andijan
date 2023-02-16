package com.sdk.domain.model.appeal


data class DataAppealResponse(
    val mahalla: Mahalla,
    val murojaatlar: List<Murojaatlar>
): java.io.Serializable