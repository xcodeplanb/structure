package com.example.structure.data.db

import androidx.room.TypeConverter
import com.example.structure.data.vo.WalkPathVo
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun pathListToJson(value: List<WalkPathVo>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToPathList(value: String) = Gson().fromJson(value, Array<WalkPathVo>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun listStringToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}