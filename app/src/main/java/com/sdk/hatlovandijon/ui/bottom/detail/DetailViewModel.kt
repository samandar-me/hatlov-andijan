package com.sdk.hatlovandijon.ui.bottom.detail

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
class DetailViewModel @Inject constructor(
    private val useCase: AllUseCases
): ViewModel() {
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Loading)
    val state: StateFlow<DetailState> get() = _state

    fun onEvent(event: DetailEvent) {
        if (event is DetailEvent.OnGetDetailImages) {
            viewModelScope.launch {
                useCase.getDetailImagesUseCase(event.id).collect { response ->
                    when(response) {
                        is Status.Loading -> _state.update { DetailState.Loading }
                        is Status.Error -> _state.update { DetailState.Error(response.message) }
                        is Status.Success -> _state.update { DetailState.Success(response.data) }
                    }
                }
            }
        }
    }
}