package com.sdk.hatlovandijon.ui.splash

import androidx.lifecycle.ViewModel
import com.sdk.domain.use_case.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: AllUseCases
): ViewModel() {

}