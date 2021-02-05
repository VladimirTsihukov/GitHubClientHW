package com.adnroidapp.githubclient.mvp.presenter

import com.adnroidapp.githubclient.mvp.navigation.Screens
import com.adnroidapp.githubclient.mvp.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}