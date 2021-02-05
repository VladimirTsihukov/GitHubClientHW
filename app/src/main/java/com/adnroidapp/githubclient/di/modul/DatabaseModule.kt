package com.adnroidapp.githubclient.di.modul

import androidx.room.Room
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.cache.IGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.adnroidapp.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): DatabaseUser = Room.databaseBuilder(
        app,
        DatabaseUser::class.java,
        DatabaseUser.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun userCache(database: DatabaseUser): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Singleton
    @Provides
    fun repositoriesCache(database: DatabaseUser): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }
}