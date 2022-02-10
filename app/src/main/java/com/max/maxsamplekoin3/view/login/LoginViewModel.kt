package com.max.maxsamplekoin3.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.max.maxsamplekoin3.model.usercase.login.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _isLoginSuccess = MutableSharedFlow<Boolean>(0)
    val isLoginSuccess = _isLoginSuccess.asSharedFlow()

    fun login(name: String) {
        viewModelScope.launch {
            loginUseCase.login(name).collect {
                _isLoginSuccess.emit(it)
            }
        }
    }
}