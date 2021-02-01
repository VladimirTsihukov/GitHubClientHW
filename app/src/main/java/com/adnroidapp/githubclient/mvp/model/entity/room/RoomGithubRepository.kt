package com.adnroidapp.githubclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class, //таблица родитель
        parentColumns = ["id"],         //прявязанное поле в таблице родителя
        childColumns = ["userId"],      //прявязанное поле в привязанной таблице
        onDelete = ForeignKey.CASCADE   //В случае удаления id родительской таблицы, удалит все данные в привязанной таблицы
    )]
)

data class RoomGithubRepository (
    @PrimaryKey
    val id: String,
    val userId: String,
    val name: String? = null,
    val forksCount: Int? = null
)