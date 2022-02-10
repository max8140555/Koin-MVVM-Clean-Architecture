package com.max.maxsamplekoin3.model.service.login

import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST

interface LoginService {
    @POST
    fun callLogin(name: String): Flow<ResponseLogin?>
}