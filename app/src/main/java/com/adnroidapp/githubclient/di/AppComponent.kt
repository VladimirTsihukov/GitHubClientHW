package com.adnroidapp.githubclient.di

import com.adnroidapp.githubclient.di.modul.*
import com.adnroidapp.githubclient.mvp.presenter.MainPresenter
import com.adnroidapp.githubclient.mvp.presenter.UserPresenter
import com.adnroidapp.githubclient.mvp.presenter.UsersPresenter
import com.adnroidapp.githubclient.ui.MainActivity
import com.adnroidapp.githubclient.ui.fragments.RepoFragment
import com.adnroidapp.githubclient.ui.fragments.UserFragment
import com.adnroidapp.githubclient.ui.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        RepoModule::class,
    ]
)
interface AppComponent {            //определяем в какие классы мы будем внедряться
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    fun inject(userPresenter: UserPresenter)
    fun inject(usersFragment: UsersFragment)
    fun inject(userFragment: UserFragment)
    fun inject(repoFragment: RepoFragment)
}