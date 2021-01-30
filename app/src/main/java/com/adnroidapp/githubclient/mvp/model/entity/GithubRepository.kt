package com.adnroidapp.githubclient.mvp.model.entity

import android.os.Parcelable
import com.adnroidapp.githubclient.mvp.model.data.UserData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository (
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null
): Parcelable

fun GithubRepository.getUserData(avatarUrl: String): UserData {
    return this.run {
        (UserData(
            id = id,
            name = name,
            forksCount = forksCount,
            avatarUrl = avatarUrl
        ))
    }
}