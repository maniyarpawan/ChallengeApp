package com.pm.challengeapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pm.challengeapp.data.local.dao.GeneratedStringDao
import com.pm.challengeapp.data.local.model.GeneratedString

@Database(entities = [GeneratedString::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun generatedStringDao(): GeneratedStringDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "generated_string_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
