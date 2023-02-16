package com.sdk.data.manager

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor(private val dataStoreManager: DataStoreManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            dataStoreManager.getToken().first()
        }
        val newReq = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(newReq)
    }
}