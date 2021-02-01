package com.adnroidapp.githubclient.mvp.model.cache

import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun getUsersRepoCacheDB(user: GithubUser) : Single<List<GithubRepository>>
    fun putUserRepoDPCache(user: GithubUser, listRepo: List<GithubRepository>) : Completable
}