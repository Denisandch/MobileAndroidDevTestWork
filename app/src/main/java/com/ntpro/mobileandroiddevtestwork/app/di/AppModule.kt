package com.ntpro.mobileandroiddevtestwork.app.di

import com.ntpro.mobileandroiddevtestwork.AppViewModel
import com.ntpro.mobileandroiddevtestwork.repository.DealRepository
import com.ntpro.mobileandroiddevtestwork.repository.DealRepositoryImplementation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DealRepository> {
        DealRepositoryImplementation(context = get())
    }

    viewModel<AppViewModel> {
        AppViewModel(
            dealRepository = get()
        )
    }
}