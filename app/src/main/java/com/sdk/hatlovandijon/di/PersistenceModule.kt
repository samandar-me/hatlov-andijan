package com.sdk.hatlovandijon.di

import android.content.Context
import androidx.room.Room
import com.sdk.data.database.AppDatabase
import com.sdk.data.database.UserDao
import com.sdk.data.manager.RequestInterceptor
import com.sdk.data.manager.SharedPrefManager
import com.sdk.data.repository.PersistenceRepositoryImpl
import com.sdk.domain.repository.PersistenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @[Provides Singleton]
    fun provideSharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @[Provides Singleton]
    fun provideInterceptor(sharedPrefManager: SharedPrefManager): RequestInterceptor {
        return RequestInterceptor(sharedPrefManager)
    }
    @[Provides Singleton]
    fun providePersistenceRepository(sharedPrefManager: SharedPrefManager, dao: UserDao): PersistenceRepository {
        return PersistenceRepositoryImpl(sharedPrefManager, dao)
    }
    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "App.Db"
        ).build()
    }
    @[Provides Singleton]
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.dao
    }
}