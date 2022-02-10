package com.max.maxsamplekoin3.model.usercase.login

import com.max.maxsamplekoin3.model.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCaseImpl(private val loginRepository: LoginRepository): LoginUseCase {

    override fun login(name: String): Flow<Boolean> {
        return loginRepository.login(name)
    }
}