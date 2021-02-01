package com.adnroidapp.githubclient.mvp.model.repo.retrofit

import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.cache.IGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.network.INetworkStatus
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val chase: IGithubUsersCache
) : IGithubUsers {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap {
                        chase.putUsersDBCache(it).toSingleDefault(it)
                    }
            } else {
                chase.getUsersCacheDB()
            }
        }.subscribeOn(Schedulers.io())
}