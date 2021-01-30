package com.adnroidapp.githubclient.mvp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val id: String,
    val name: String? = null,
    val forksCount: Int? = null,
    val avatarUrl: String,
) : Parcelable
