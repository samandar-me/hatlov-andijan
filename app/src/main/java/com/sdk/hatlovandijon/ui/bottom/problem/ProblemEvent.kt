package com.sdk.hatlovandijon.ui.bottom.problem

import com.sdk.domain.model.update.UpdateAppealRequest
import com.sdk.domain.model.upload.AddAppealRequest

sealed class ProblemEvent {
    data class OnSaveAppeal(val appealRequest: AddAppealRequest): ProblemEvent()
    object OnSearchAppealType: ProblemEvent()
    data class OnUpdateAppeal(val updateAppealRequest: UpdateAppealRequest): ProblemEvent()
}