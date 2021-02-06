package com.adnroidapp.githubclient.di.modul

import androidx.room.Room
import com.adnroidapp.githubclient.App
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
}