package com.ntpro.mobileandroiddevtestwork.app.di

import com.ntpro.mobileandroiddevtestwork.AppViewModel
import com.ntpro.mobileandroiddevtestwork.repository.DealRepository
import com.ntpro.mobileandroiddevtestwork.repository.DealRepositoryImplementation
import com.ntpro.mobileandroiddevtestwork.usecase.AddDataToDBUseCase
import com.ntpro.mobileandroiddevtestwork.usecase.GetNewDealsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DealRepository> {
        DealRepositoryImplementation(context = get())
    }

    single<AddDataToDBUseCase> {
        AddDataToDBUseCase(repository = get())
    }

    single<GetNewDealsUseCase> {
        GetNewDealsUseCase(repository = get())
    }

    viewModel<AppViewModel> {
        AppViewModel(
            addDealsToDBUseCase = get(),
            getNewDealsUseCase = get()
        )
    }
}