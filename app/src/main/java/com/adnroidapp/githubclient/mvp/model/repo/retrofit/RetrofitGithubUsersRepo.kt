package com.adnroidapp.githubclient.mvp.model.repo.retrofit

import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import com.adnroidapp.githubclient.mvp.model.entity.room.RoomGithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.room.RoomGithubUser
import com.adnroidapp.githubclient.mvp.model.network.INetworkStatus
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: DatabaseUser
) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    Single.fromCallable {
                        val roomUser = users.map { user ->
                            RoomGithubUser(
                                user.id ?: "",
                                user.login ?: "",
                                user.avatarUrl ?: "", user.reposUrl ?: ""
                            )
                        }
                        db.userDao().insert(roomUser)
                        users
                    }
                }
            } else {
                Single.fromCallable {
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

        }.subscribeOn(Schedulers.io())

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url).flatMap { usersRepo ->
                        Single.fromCallable {
                            val roomRepo = usersRepo.map { userRepo ->
                                RoomGithubRepository(
                                    id = userRepo.id,
                                    userId = user.id,
                                    name = userRepo.name ?: "",
                                    forksCount = userRepo.forksCount ?: -1
                                )
                            }
                            db.repositoryDao().insert(roomRepo)
                            usersRepo
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUSer = user.login.let { db.userDao().findByLogin(it) }
                    db.repositoryDao().findByLogin(roomUSer.id)
                        .map { GithubRepository(it.id, it.name, it.forksCount) }
                }
            }
        }.subscribeOn(Schedulers.io())
}