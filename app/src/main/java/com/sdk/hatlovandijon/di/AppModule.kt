package com.sdk.hatlovandijon.di

import com.sdk.data.network.login.LoginService
import com.sdk.data.repository.LoginRepositoryImpl
import com.sdk.domain.repository.LoginRepository
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.use_case.login.LoginUseCase
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

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {
    @[Provides Singleton]
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
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
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }.build()
    }
    @[Provides Singleton]
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

    @[Provides Singleton]
    fun provideAllUseCases(loginRepository: LoginRepository): AllUseCases {
        return AllUseCases(
            loginUseCase = LoginUseCase(loginRepository)
        )
    }
}
