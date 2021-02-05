package com.adnroidapp.githubclient.mvp.navigation

import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.ui.fragments.RepoFragment
import com.adnroidapp.githubclient.ui.fragments.UserFragment
import com.adnroidapp.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepoScreen(val user: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = RepoFragment.newInstance(user)
    }
}