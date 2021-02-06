package com.adnroidapp.githubclient.di

import com.adnroidapp.githubclient.di.modul.*
import com.adnroidapp.githubclient.di.user.UserSubComponent
import com.adnroidapp.githubclient.mvp.presenter.MainPresenter
import com.adnroidapp.githubclient.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ImageModule::class,
    ]
)
interface AppComponent {
    fun userSubComponent(): UserSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}