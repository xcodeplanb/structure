package com.example.structure.data.vo

import android.location.Location

data class WalkPathVo(
    val location: Location,
    val state: Int,
    val lat: String,
    val lng: String,
    val createDatetimeMilli: Long
)