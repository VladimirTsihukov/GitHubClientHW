package com.adnroidapp.githubclient.mvp.model.entity.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adnroidapp.githubclient.mvp.model.entity.room.dao.RepositoryDao
import com.adnroidapp.githubclient.mvp.model.entity.room.dao.UserDao

@Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class DatabaseUser: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}