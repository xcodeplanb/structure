package com.example.structure.data.db.walk

import javax.inject.Inject

class WalkDBRepository @Inject constructor(private val waklDao: WalkDao) {
    suspend fun insert(walk: Walk) = waklDao.insert(walk)
    suspend fun delete(userId: String) = waklDao.delete(userId)
    suspend fun deleteAll() = waklDao.deleteAll()
    suspend fun select(userId: String) = waklDao.select(userId)
    suspend fun selectAll() = waklDao.selectAll()
}