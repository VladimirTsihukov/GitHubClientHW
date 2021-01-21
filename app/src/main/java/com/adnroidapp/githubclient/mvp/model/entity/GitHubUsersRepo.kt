package com.adnroidapp.githubclient.mvp.model.entity

import io.reactivex.rxjava3.core.Observable

class GitHubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
    )

    fun getUsers(): Observable<GithubUser> {
        return Observable.fromIterable(repositories)
    }
}