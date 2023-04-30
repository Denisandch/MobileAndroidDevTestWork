package com.ntpro.mobileandroiddevtestwork.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<UserService> {
        UserServiceImplementation(get())
    }

    viewModel<AppViewModel> {
        AppViewModel(
            userService = get()
        )
    }
}