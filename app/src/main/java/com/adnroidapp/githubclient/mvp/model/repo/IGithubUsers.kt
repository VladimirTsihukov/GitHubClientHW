package com.adnroidapp.githubclient.mvp.model.repo

import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import dagger.Provides
import io.reactivex.rxjava3.core.Single

interface IGithubUsers {
    fun getUsers(): Single<List<GithubUser>>
}