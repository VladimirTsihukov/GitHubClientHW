package com.adnroidapp.githubclient.mvp.model.entity.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adnroidapp.githubclient.mvp.model.entity.room.dao.RepositoryDao
import com.adnroidapp.githubclient.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class DatabaseUser: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: DatabaseUser? = null

        fun getInstance() = instance?: throw RuntimeException("База данных не создана")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    DatabaseUser::class.java,
                    DB_NAME
                    ).build()
            }
        }
    }
}