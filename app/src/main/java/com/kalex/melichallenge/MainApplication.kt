package com.kalex.melichallenge

import android.app.Application
import com.kalex.melichallenge.di.appModule
import com.kalex.melichallenge.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, searchModule)
        }
    }
}