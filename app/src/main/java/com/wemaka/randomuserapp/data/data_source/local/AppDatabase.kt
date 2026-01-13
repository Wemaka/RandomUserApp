package com.wemaka.randomuserapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wemaka.randomuserapp.data.data_source.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}