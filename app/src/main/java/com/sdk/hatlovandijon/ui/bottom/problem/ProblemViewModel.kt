package com.sdk.hatlovandijon.ui.bottom.problem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.model.search.SearchData
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import com.sdk.hatlovandijon.util.Constants.TAG
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
    private val _searchData: MutableStateFlow<List<SearchData>> = MutableStateFlow(emptyList())
    val searchData: StateFlow<List<SearchData>> get() = _searchData

    fun onEvent(event: ProblemEvent) {
        when(event ) {
            is ProblemEvent.OnSaveAppeal -> {
                viewModelScope.launch {
                    useCases.addAppealUseCase(event.appealRequest).collect { response ->
                        Log.d(TAG, "onEvent: $response")
                        when (response) {
                            is Status.Loading -> _state.update { ProblemState.Loading }
                            is Status.Error -> _state.update { ProblemState.Error(response.message) }
                            is Status.Success -> _state.update { ProblemState.Success }
                        }
                    }
                }
            }
            is ProblemEvent.OnSearchAppealType -> {
                viewModelScope.launch {
                    useCases.searchAppealTypeUseCase(event.query).collect {
                        _searchData.value = it
                    }
                }
            }
        }
    }
}