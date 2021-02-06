package com.adnroidapp.githubclient.di.repository.module

import com.adnroidapp.githubclient.di.repository.RepoScope
import com.adnroidapp.githubclient.mvp.model.api.IDataSource
import com.adnroidapp.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.cache.IGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import com.adnroidapp.githubclient.mvp.model.network.INetworkStatus
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsers
import com.adnroidapp.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsers
import dagger.Module
import dagger.Provides

@Module
class RepoModule {
    @Provides
    fun repositoriesCache(database: DatabaseUser): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

    @RepoScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsers {
      return RetrofitGithubUsers(api, networkStatus, cache)
    }
}