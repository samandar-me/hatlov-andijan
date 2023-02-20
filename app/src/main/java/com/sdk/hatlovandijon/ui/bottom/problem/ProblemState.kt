package com.sdk.hatlovandijon.ui.bottom.problem

sealed class ProblemState {
    object Loading: ProblemState()
    object Idle: ProblemState()
    data class Error(val message: String): ProblemState()
    object Success: ProblemState()

    object SuccessUpdate: ProblemState()
}