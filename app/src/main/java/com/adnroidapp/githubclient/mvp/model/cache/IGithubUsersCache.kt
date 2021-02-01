package com.adnroidapp.githubclient.mvp.model.cache

import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun getUsersCacheDB(): Single<List<GithubUser>>
    fun putUsersDBCache(user: List<GithubUser>): Completable
}