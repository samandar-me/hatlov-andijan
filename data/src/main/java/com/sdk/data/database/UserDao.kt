package com.sdk.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdk.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE userId = :id")
    fun getUser(id: Int): Flow<UserEntity?>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}