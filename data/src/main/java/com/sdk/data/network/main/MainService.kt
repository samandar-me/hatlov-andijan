package com.sdk.data.network.main

import com.sdk.data.model.VariablesResponse
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.detail.DetailImageResponse
import com.sdk.domain.model.upload.AddAppealResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface MainService {
    @GET("api/v1/variables/")
    suspend fun getVariables(): Response<VariablesResponse>

    @GET("api/v1/dashboard/")
    suspend fun getAppeals(): Response<AppealResponse>

    @GET("api/v1/murojaat/detail/{id}")
    suspend fun getDetailImages(
        @Path("id") id: Int
    ): Response<DetailImageResponse>

    @POST("api/v1/murojaat/add/")
    @Multipart
    suspend fun addAppeal(
        @Part("address") address: String,
        @Part("owner_home_name") ownerHomeName: String,
        @Part("owner_home_year") ownerHomeYear: Int,
        @Part("owner_home_jinsi") ownerHomeGender: Int,
        @Part("owner_home_phone") ownerHomePhone: String,
        @Part("owner_as_speaker") ownerAsSpeaker: Int,
        @Part("speaker_name") speakerName: String,
        @Part("speaker_year") speakerYear: String,
        @Part("speaker_jinsi") speakerGender: String,
        @Part("speaker_phone") speakerPhone: String,
        @Part("turi") type: Int,
        @Part("izoh") comment: String,
        @Part images: List<MultipartBody.Part>
    ): Response<AddAppealResponse>
}