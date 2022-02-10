package com.max.maxsamplekoin3.model.repository.login

import com.max.maxsamplekoin3.model.data.User
import com.max.maxsamplekoin3.model.service.login.LoginService
import com.max.maxsamplekoin3.model.service.login.ResponseLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LoginRepositoryImpl(
    private val user: User,
    private val apiService: LoginService
): LoginRepository {

    override fun login(name: String): Flow<Boolean> {
        // real
//        return apiService.callLogin(name)
//            .map { response ->
//
//                if (response == null) {
//                    false
//                } else {
//                    setUserInfo(response)
//                    true
//                }
//
//            }.flowOn(Dispatchers.IO)

        // Mock
        return flowOf(ResponseLogin(name, "Token_$name")).map { response ->
            setUserInfo(response)
            true
        }.flowOn(Dispatchers.IO)
    }

    private fun setUserInfo(responseLogin: ResponseLogin) {
        user.apply {
            userName = responseLogin.userName
            token = responseLogin.token
        }
    }
}