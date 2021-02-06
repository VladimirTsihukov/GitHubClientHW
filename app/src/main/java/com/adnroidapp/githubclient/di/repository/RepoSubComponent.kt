package com.adnroidapp.githubclient.di.repository

import com.adnroidapp.githubclient.di.repository.module.RepoModule
import com.adnroidapp.githubclient.mvp.presenter.RepoPresenter
import com.adnroidapp.githubclient.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepoScope
@Subcomponent(modules = [RepoModule::class])
interface RepoSubComponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)
}