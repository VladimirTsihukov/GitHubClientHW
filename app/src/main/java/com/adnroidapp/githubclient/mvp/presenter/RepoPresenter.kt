package com.adnroidapp.githubclient.mvp.presenter

import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.view.RepoView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(private val gitRepo: GithubRepository, val router: Router): MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setId(gitRepo.id)
        viewState.setName(gitRepo.name ?: "")
        viewState.setForksCount((gitRepo.forksCount ?: 0).toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}