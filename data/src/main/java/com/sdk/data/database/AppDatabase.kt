package com.sdk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.domain.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: UserDao
}