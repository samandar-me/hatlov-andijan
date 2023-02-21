package com.sdk.hatlovandijon.ui.bottom.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import com.sdk.hatlovandijon.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: AllUseCases,
) : ViewModel() {
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Loading)
    val state: StateFlow<DetailState> get() = _state

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.OnGetDetailData -> {
                viewModelScope.launch {
                    useCase.getDetailImagesUseCase(event.id).collect { status ->
                        when(status) {
                            is Status.Loading -> _state.update { DetailState.Loading }
                            is Status.Error -> _state.update { DetailState.Error(status.message) }
                            is Status.Success -> _state.update { DetailState.Success(status.data) }
                        }
                    }
                }
            }
            is DetailEvent.OnDeleteImage -> {
                viewModelScope.launch {
                    useCase.deleteImageUseCase(event.id)
                }
            }
        }
    }
}