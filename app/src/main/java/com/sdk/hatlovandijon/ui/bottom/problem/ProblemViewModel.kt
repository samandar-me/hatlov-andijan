package com.sdk.hatlovandijon.ui.bottom.problem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProblemViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<ProblemState> = MutableStateFlow(ProblemState.Idle)
    val state: StateFlow<ProblemState> get() = _state

    fun onEvent(event: ProblemEvent) {
        if (event is ProblemEvent.OnSaveAppeal) {
            viewModelScope.launch {
                useCases.addAppealUseCase(event.appealRequest).collect { response ->
                    when (response) {
                        is Status.Loading -> _state.update { ProblemState.Loading }
                        is Status.Error -> _state.update { ProblemState.Error(response.message) }
                        is Status.Success -> _state.update { ProblemState.Success }
                    }
                }
            }
        }
    }
}