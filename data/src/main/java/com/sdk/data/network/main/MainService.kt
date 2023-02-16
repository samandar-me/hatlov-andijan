package com.sdk.data.network.main

import com.sdk.data.model.VariablesResponse
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.detail.DetailImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService  {
    @GET("api/v1/variables/")
    suspend fun getVariables(): Response<VariablesResponse>

    @GET("api/v1/dashboard/")
    suspend fun getAppeals(): Response<AppealResponse>

    @GET("api/v1/murojaat/detail/{id}")
    suspend fun getDetailImages(
        @Path("id") id: Int
    ): Response<DetailImageResponse>
}