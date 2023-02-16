package com.sdk.hatlovandijon.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.model.UserEntity
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import com.sdk.hatlovandijon.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val allUseCases: AllUseCases,
) : ViewModel() {
    private val _state: MutableStateFlow<LoginActivityState> =
        MutableStateFlow(LoginActivityState.Idle)
    val state: StateFlow<LoginActivityState> get() = _state

    fun onEvent(event: LoginActivityEvent) {
        when (event) {
            is LoginActivityEvent.OnLoginClicked -> {
                viewModelScope.launch {
                    allUseCases.loginUseCase(event.loginData).collect { status ->
                        when(status) {
                            is Status.Loading -> {
                                _state.update {
                                    LoginActivityState.Loading
                                }
                            }
                            is Status.Error -> _state.update {
                                LoginActivityState.Error(status.message)
                            }
                            is Status.Success -> {
                                val user = Triple(event.loginData,status.data.access, status.data.userId)
                                Log.d(TAG, "onEvent: $user")
                                allUseCases.saveTokenUseCase(user)
                                allUseCases.saveUserUseCase(
                                    UserEntity(
                                        refreshToken = status.data.refresh,
                                        accessToken = status.data.access,
                                        userId = status.data.userId,
                                        userName = status.data.userName,
                                        fullName = status.data.fullName,
                                        roleName = status.data.roleName,
                                        roleId = status.data.roleId,
                                        neiName = status.data.neiName,
                                        neiId = status.data.neiId
                                    )
                                )
                                _state.update {
                                    LoginActivityState.Success(status.data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}