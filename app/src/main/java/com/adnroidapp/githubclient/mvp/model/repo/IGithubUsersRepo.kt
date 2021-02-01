package com.adnroidapp.githubclient.mvp.model.repo

import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}