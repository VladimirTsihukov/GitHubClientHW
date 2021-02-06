package com.adnroidapp.githubclient.di.user

import com.adnroidapp.githubclient.di.repository.RepoSubComponent
import com.adnroidapp.githubclient.di.user.module.UserModule
import com.adnroidapp.githubclient.mvp.presenter.UsersPresenter
import com.adnroidapp.githubclient.ui.adapter.AdapterUsers
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserSubComponent {
    fun repoSubComponent(): RepoSubComponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersAdapter: AdapterUsers)
}