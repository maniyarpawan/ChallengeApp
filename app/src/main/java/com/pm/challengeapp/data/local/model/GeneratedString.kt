package com.pm.challengeapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "generated_strings")
data class GeneratedString(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val length: Int,
    val createdAt: String
)
