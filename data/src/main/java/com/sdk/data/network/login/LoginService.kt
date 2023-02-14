package com.sdk.data.network.login

import com.sdk.data.model.LoginResponseDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("api/v1/login/")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") password: String
    ): Response<LoginResponseDTO>
}