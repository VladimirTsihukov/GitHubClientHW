package com.adnroidapp.githubclient

import android.app.Application
import com.adnroidapp.githubclient.di.AppComponent
import com.adnroidapp.githubclient.di.modul.AppModule
import com.adnroidapp.githubclient.di.repository.RepoSubComponent
import com.adnroidapp.githubclient.di.user.UserSubComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    var userSubComponent: UserSubComponent? = null
        private set

    var repoSubComponent: RepoSubComponent? = null
        private set

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

    fun initUserSubComponent() = appComponent.userSubComponent().also {
        userSubComponent = it
    }

    fun releaseUserSubComponent() {
        userSubComponent = null
    }

    fun initRepoSubComponent() = userSubComponent?.repoSubComponent().also {
        repoSubComponent = it
    }

    fun releaseRepoSubComponent() {
        repoSubComponent = null
    }
}