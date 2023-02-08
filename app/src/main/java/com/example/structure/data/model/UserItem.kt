package com.example.structure.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(
    val name: String = "",
    val avatarUrl: String = ""
) : Parcelable