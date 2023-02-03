package com.example.structure.data.db
import com.example.structure.data.db.walk.Walk
import com.example.structure.data.db.walk.WalkDao
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database

@Database(entities = [Walk::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getWalkDao(): WalkDao
}