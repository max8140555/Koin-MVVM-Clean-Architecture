package com.max.maxsamplekoin3.di

import com.max.maxsamplekoin3.view.home.HomeViewModel
import com.max.maxsamplekoin3.view.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}