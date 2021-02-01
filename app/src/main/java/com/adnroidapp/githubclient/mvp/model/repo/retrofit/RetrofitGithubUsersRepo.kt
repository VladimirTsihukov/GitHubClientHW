package com.adnroidapp.githubclient.mvp.model.repo.retrofit

import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.network.INetworkStatus
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubRepositoriesCache
) : IGithubUsersRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap {
                            cache.putUserRepoDPCache(user, it).toSingleDefault(it)
                        }
                }

            } else {
                cache.getUsersRepoCacheDB(user)
            }
        }.subscribeOn(Schedulers.io())
}