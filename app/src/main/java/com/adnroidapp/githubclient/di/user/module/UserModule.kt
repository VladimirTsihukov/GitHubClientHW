package com.adnroidapp.githubclient.di.user.module

import com.adnroidapp.githubclient.di.user.UserScope
import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.cache.IGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import com.adnroidapp.githubclient.mvp.model.network.INetworkStatus
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo
import com.adnroidapp.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @UserScope
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @UserScope
    @Provides
    fun userCache(database: DatabaseUser): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }
}