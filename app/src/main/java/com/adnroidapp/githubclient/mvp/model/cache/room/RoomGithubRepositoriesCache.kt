package com.adnroidapp.githubclient.mvp.model.cache.room

import com.adnroidapp.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import com.adnroidapp.githubclient.mvp.model.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(
    private val db: DatabaseUser
) : IGithubRepositoriesCache {

    override fun getUsersRepoCacheDB(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val room = db.userDao().findByLogin(user.login)
            return@fromCallable db.repositoryDao().findByLogin(room.id).map {
                GithubRepository(id = it.id, name = it.name, forksCount = it.forksCount)
            }
        }.subscribeOn(Schedulers.io())

    override fun putUserRepoDPCache(
        user: GithubUser,
        listRepo: List<GithubRepository>
    ): Completable =
        Completable.fromAction {
            val roomUSer = db.userDao().findByLogin(user.login)
            val roomRepos = listRepo.map {
                RoomGithubRepository(
                    id = it.id,
                    name = it.name,
                    forksCount = it.forksCount,
                    userId = roomUSer.id
                )
            }
            db.repositoryDao().insert(roomRepos)
        }.subscribeOn(Schedulers.io())
}