package com.example.structure.data.db.walk

import com.example.structure.data.db.walk.Walk
import com.example.structure.data.db.walk.WalkDao
import javax.inject.Inject

class WalkDBRepository @Inject constructor(private val waklDao: WalkDao) {
    suspend fun insert(walk: Walk) = waklDao.insert(walk)
    suspend fun delete(user_id: String) = waklDao.delete(user_id)
    suspend fun deleteAll() = waklDao.deleteAll()
    suspend fun select(user_id: String) = waklDao.select(user_id)
    suspend fun selectUserId(user_id: String) = waklDao.selectUserId(user_id)
    suspend fun selectAll() = waklDao.selectAll()
}