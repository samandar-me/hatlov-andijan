package com.sdk.hatlovandijon.ui.bottom.problem

import com.sdk.domain.model.upload.AddAppealRequest

sealed class ProblemEvent {
    data class OnSaveAppeal(val appealRequest: AddAppealRequest): ProblemEvent()
    data class OnSearchAppealType(val query: String): ProblemEvent()
}