package com.max.maxsamplekoin3.model.repository.login

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(name: String): Flow<Boolean>
}