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
        if (event is DetailEvent.OnGetDetailImages) {
            viewModelScope.launch {
                useCase.getDetailImagesUseCase(event.id)
                    .onStart {
                        _state.update {
                            DetailState.Loading
                        }
                    }
                    .catch {
                        _state.update { DetailState.Error(it.toString()) }
                    }
                    .collect { list ->
                        list?.let {images ->
                            _state.update { DetailState.Success(images) }
                        }
                    }
            }
        }
    }
}