package com.adnroidapp.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun initRepoAdapter()
    fun updateList()
    fun showSnackBarForkCount(forkCount: Int)
}