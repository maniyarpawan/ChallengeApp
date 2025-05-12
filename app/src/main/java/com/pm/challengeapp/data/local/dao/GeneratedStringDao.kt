package com.pm.challengeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pm.challengeapp.data.local.model.GeneratedString
import kotlinx.coroutines.flow.Flow

@Dao
interface GeneratedStringDao {
    @Query("SELECT * FROM generated_strings ORDER BY id DESC")
    fun getAll(): Flow<List<GeneratedString>>

    @Insert
    suspend fun insert(generatedString: GeneratedString)

    @Delete
    suspend fun delete(generatedString: GeneratedString)

    @Query("DELETE FROM generated_strings")
    suspend fun deleteAll()
}
