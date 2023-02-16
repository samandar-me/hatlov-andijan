package com.sdk.hatlovandijon.di

import android.content.Context
import androidx.room.Room
import com.sdk.data.database.AppDatabase
import com.sdk.data.database.UserDao
import com.sdk.data.manager.DataStoreManager
import com.sdk.data.manager.RequestInterceptor
import com.sdk.data.repository.PersistenceRepositoryImpl
import com.sdk.domain.repository.PersistenceRepository
import com.sdk.hatlovandijon.util.NetworkHelper
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
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @[Provides Singleton]
    fun provideInterceptor(dataStoreManager: DataStoreManager): RequestInterceptor {
        return RequestInterceptor(dataStoreManager)
    }
    @[Provides Singleton]
    fun providePersistenceRepository(dataStoreManager: DataStoreManager, dao: UserDao): PersistenceRepository {
        return PersistenceRepositoryImpl(dataStoreManager, dao)
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

    @[Provides Singleton]
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}