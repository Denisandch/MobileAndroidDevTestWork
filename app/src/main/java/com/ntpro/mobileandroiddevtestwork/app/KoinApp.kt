package com.ntpro.mobileandroiddevtestwork.app

import android.app.Application
import com.ntpro.mobileandroiddevtestwork.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApp)
            modules(listOf(appModule))
        }
    }
}