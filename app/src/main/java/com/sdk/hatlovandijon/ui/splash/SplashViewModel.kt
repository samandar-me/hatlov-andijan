package com.sdk.hatlovandijon.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.data.manager.DataStoreManager
import com.sdk.domain.model.LoginData
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import com.sdk.hatlovandijon.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Idle)
    val state: StateFlow<SplashState> get() = _state

    init {
        checkForUser()
    }

    fun checkForUser() {
        viewModelScope.launch {
            useCases.getVariableUseCase.invoke(Unit).collect { status ->
                when (status) {
                    is Status.Loading -> {
                        delay(1000L)
                    }
                    is Status.Error -> {
                        if (status.message == "401") {
                            if (dataStoreManager.getUserId().first() == null) {
                                _state.update { SplashState.UserNotAuthed }
                            } else {
                                login()
                            }
                        }
                    }
                    is Status.Success -> {
                        _state.update { SplashState.UserAuthed }
                    }
                }
            }
        }
    }

    private suspend fun login() {
        val user = dataStoreManager.getUser().first()
        Log.d(TAG, "login: $user")
        useCases.loginUseCase(LoginData(user.userName, user.password)).collect { status ->
            when (status) {
                is Status.Loading -> Unit
                is Status.Error -> Unit
                is Status.Success -> {
                    useCases.saveTokenUseCase(
                        Triple(
                            LoginData(user.userName, user.password),
                            status.data.access,
                            status.data.userId
                        )
                    )
                    _state.update { SplashState.UserAuthed }
                }
            }
        }
    }
}