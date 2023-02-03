package com.example.structure.data.vo

data class WalkPath(
    val location: MyLocation,
    var state: Int,
    var lat: String,
    var lng: String,
    var createDatetimeMilli: Long
)

/**
 * oreo 에서 구글 location 객체에서 종종 파싱 에러가 나타남
 */
data class MyLocation(
    var lat: Double,
    var lng: Double,
    var time: Long = 0
)