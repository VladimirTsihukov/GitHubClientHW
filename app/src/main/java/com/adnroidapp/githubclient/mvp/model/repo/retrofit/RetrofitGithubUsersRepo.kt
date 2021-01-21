package com.adnroidapp.githubclient.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(private val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}