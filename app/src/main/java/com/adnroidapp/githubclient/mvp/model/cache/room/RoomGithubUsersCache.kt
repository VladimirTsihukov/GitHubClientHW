package com.adnroidapp.githubclient.mvp.model.cache.room

import com.adnroidapp.githubclient.mvp.model.cache.IGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(
    private val db: DatabaseUser
) : IGithubUsersCache {

    override fun getUsersCacheDB(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao().getAll().map {
            GithubUser(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                reposUrl = it.reposUrl
            )
        }
    }

    override fun putUsersDBCache(user: List<GithubUser>): Completable =
        Completable.fromCallable {
            db.userDao().getAll().map { roomUser ->
                GithubUser(
                    roomUser.id,
                    roomUser.login,
                    roomUser.avatarUrl,
                    roomUser.reposUrl
                )
            }
        }
}