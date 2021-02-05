package com.adnroidapp.githubclient

import android.app.Application
import com.adnroidapp.githubclient.di.AppComponent
import com.adnroidapp.githubclient.di.DaggerAppComponent
import com.adnroidapp.githubclient.di.modul.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}