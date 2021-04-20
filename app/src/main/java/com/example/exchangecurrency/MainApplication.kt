package com.example.exchangecurrency

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@MainApplication)
//            modules(listOf(appModule, repositoryModule, viewModelModule))
//        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}