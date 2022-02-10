package com.max.maxsamplekoin3

import android.app.Application
import com.max.maxsamplekoin3.di.*
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
//            androidContext(this@MyApp)
            if (BuildConfig.DEBUG) {
                androidLogger(Level.NONE)
            }
            modules(
                listOf(
                    viewModelModule,
                    appCoreModule,
                    domainModule,
                    domainUserCaseModule,
                    domainRepositoryModule
                )
            )
        }
    }
}