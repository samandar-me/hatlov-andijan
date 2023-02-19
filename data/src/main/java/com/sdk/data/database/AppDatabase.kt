package com.sdk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.domain.model.UserEntity
import com.sdk.domain.model.VariableStatus

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: UserDao
}