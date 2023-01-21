package com.example.structure.data.db.walk

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.structure.data.vo.WalkPath

@Entity(tableName = "walk")
data class Walk(
    @PrimaryKey()
    @ColumnInfo(name = "walk_id")
    var walkId: Int = -1,

    @ColumnInfo(name = "user_id")
    var userId: String = "",

    @ColumnInfo(name = "pet_ids")
    var petIds: List<Int> = listOf(),

    @ColumnInfo(name = "distance")
    var distance: Double = 0.0,

    @ColumnInfo(name = "time")
    var time: String = "",

    @ColumnInfo(name = "path")
    var path: List<WalkPath> = listOf(),

    @ColumnInfo(name = "start_lat")
    var startLat: Double = 0.0,

    @ColumnInfo(name = "start_lng")
    var startLng: Double = 0.0,

    @ColumnInfo(name = "end_lat")
    var endLat: Double = 0.0,

    @ColumnInfo(name = "end_lng")
    var endLng: Double = 0.0,

    @ColumnInfo(name = "end_state")
    var endState: Int = 2,

    @ColumnInfo(name = "async_success")
    var asyncSuccess: Int = 0,

    @ColumnInfo(name = "walk_end_datetime_milli")
    var walkEndDatetimeMilli: Long = 0,

    @ColumnInfo(name = "pictures")
    var pictures: List<String> = listOf()
)