package com.sdk.hatlovandijon.ui.bottom.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.data.manager.DataStoreManager
import com.sdk.domain.model.UserEntity
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import com.sdk.hatlovandijon.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val dataStoreManager: DataStoreManager
): ViewModel() {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state: StateFlow<MainState> get() = _state
    private val _user: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val user: StateFlow<UserEntity?> get() = _user
    val savedAppealList = mutableListOf<Murojaatlar>()

    init {
        onEvent(MainEvent.OnGetAppeals)
        viewModelScope.launch {
            Log.d(TAG, "mainViewModel: ${dataStoreManager.getToken().first()}")
            useCases.getUserUseCase(dataStoreManager.getUserId().first()!!).collect {
                Log.d(TAG, "observeUser: $it")
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
                            is Status.Success ->{
                                savedAppealList.clear()
                                savedAppealList.addAll(response.data.data.murojaatlar)
                                _state.update {
                                    MainState.SuccessAppeals(response.data)
                                }
                            }
                        }
                    }
                }
            }
            is MainEvent.OnSearchAppeal -> {
                viewModelScope.launch {
                    useCases.searchAppealDashboardUseCase.invoke(event.query).collect { response ->
                        when(response) {
                            is Status.Loading -> _state.value = MainState.Loading
                            is Status.Error -> _state.update { MainState.Error(response.message) }
                            is Status.Success -> _state.update { MainState.SuccessSearchAppeals(response.data) }
                        }
                    }
                }
            }
        }
    }
}