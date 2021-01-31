package com.adnroidapp.githubclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser (
    @PrimaryKey
    val id: String,
    val login : String,
    val avatarUrl: String? = null,
    val reposUrl: String? = null
)