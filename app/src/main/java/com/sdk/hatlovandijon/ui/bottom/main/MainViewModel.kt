package com.sdk.hatlovandijon.ui.bottom.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.data.manager.SharedPrefManager
import com.sdk.domain.model.UserEntity
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val sharedPrefManager: SharedPrefManager
): ViewModel() {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state: StateFlow<MainState> get() = _state
    private val _user: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val user: StateFlow<UserEntity?> get() = _user

    init {
        println("${sharedPrefManager.getToken()}")
        onEvent(MainEvent.OnGetAppeals)
        viewModelScope.launch {
            useCases.getUserUseCase(sharedPrefManager.getUserId()).collect {
                _user.value = it
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnGetAppeals -> {
                viewModelScope.launch {
                    useCases.getAppealsUseCase.invoke(Unit).collect { response ->
                        when(response) {
                            is Status.Loading -> _state.update { MainState.Loading }
                            is Status.Error -> _state.update { MainState.Error(response.message) }
                            is Status.Success -> _state.update { MainState.SuccessAppeals(response.data) }
                        }
                    }
                }
            }
        }
    }
}