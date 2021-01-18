package com.adnroidapp.githubclient.mvp.presenter

import com.adnroidapp.githubclient.mvp.view.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter (private val router: Router, private val nameUser: String): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setNameUser(nameUser)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}