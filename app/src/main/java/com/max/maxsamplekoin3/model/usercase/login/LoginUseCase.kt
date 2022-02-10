package com.max.maxsamplekoin3.model.usercase.login

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    fun login(name:String): Flow<Boolean>
}