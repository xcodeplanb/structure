package com.example.structure.data.db.walk

import androidx.room.*

@Dao
interface WalkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(walk: Walk)

    @Query("SELECT * FROM walk WHERE user_id = :key")
    suspend fun select(key: String): Walk?

    @Query("SELECT * FROM walk ORDER BY walk_id DESC LIMIT 1")
    suspend fun selectAll(): List<Walk>

    @Query("DELETE FROM walk WHERE user_id = :key")
    suspend fun delete(key: String)

    @Query("DELETE FROM walk")
    suspend fun deleteAll()
}