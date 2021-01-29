package com.adnroidapp.githubclient.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single

class RetrofitGithubUsersRepo(private val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())
}