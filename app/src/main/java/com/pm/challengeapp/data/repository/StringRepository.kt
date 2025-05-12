package com.pm.challengeapp.data.repository

import com.pm.challengeapp.data.local.dao.GeneratedStringDao
import com.pm.challengeapp.data.local.model.GeneratedString

class StringRepository(private val dao: GeneratedStringDao) {
    val allStrings = dao.getAll()

    suspend fun insert(string: GeneratedString) = dao.insert(string)
    suspend fun delete(string: GeneratedString) = dao.delete(string)
    suspend fun deleteAll() = dao.deleteAll()
}
