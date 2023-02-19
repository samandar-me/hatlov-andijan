package com.sdk.hatlovandijon.di

import com.sdk.data.manager.RequestInterceptor
import com.sdk.data.network.login.LoginService
import com.sdk.data.network.main.MainService
import com.sdk.data.repository.LoginRepositoryImpl
import com.sdk.data.repository.MainRepositoryImpl
import com.sdk.domain.repository.LoginRepository
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.repository.PersistenceRepository
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.use_case.login.LoginUseCase
import com.sdk.domain.use_case.main.*
import com.sdk.hatlovandijon.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [PersistenceModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {
    @[Provides Singleton]
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @[Provides Singleton]
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @[Provides Singleton]
    fun provideOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            addInterceptor(requestInterceptor)
        }.build()
    }

    @[Provides Singleton]
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

    @[Provides Singleton]
    fun provideAllUseCases(
        loginRepository: LoginRepository,
        persistenceRepository: PersistenceRepository,
        mainRepository: MainRepository,
    ): AllUseCases {
        return AllUseCases(
            loginUseCase = LoginUseCase(loginRepository),
            saveTokenUseCase = SaveTokenUseCase(persistenceRepository),
            getVariableUseCase = GetVariableUseCase(mainRepository),
            getAppealsUseCase = GetAppealsUseCase(mainRepository),
            getUserUseCase = GetUserUseCase(persistenceRepository),
            saveUserUseCase = SaveUserUseCase(persistenceRepository),
            getDetailImagesUseCase = GetDetailImagesUseCase(mainRepository),
            addAppealUseCase = AddAppealUseCase(mainRepository),
            searchAppealTypeUseCase = SearchAppealTypeUseCase(mainRepository),
            searchAppealDashboardUseCase = SearchAppealDashboardUseCase(mainRepository)
        )
    }

    @[Provides Singleton]
    fun provideMainRepository(mainService: MainService): MainRepository {
        return MainRepositoryImpl(mainService)
    }
}
