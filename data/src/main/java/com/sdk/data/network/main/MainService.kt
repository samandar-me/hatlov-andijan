package com.sdk.data.network.main

import com.sdk.domain.model.VariablesResponse
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.detail.DetailResponse
import com.sdk.domain.model.filter.FilterResponse
import com.sdk.domain.model.search.SearchResponse
import com.sdk.domain.model.upload.AddAppealResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MainService {
    @GET("")
    suspend fun getVariables(): Response<VariablesResponse>

    @GET("")
    suspend fun getAppeals(): Response<AppealResponse>

    @GET("")
    suspend fun getDetailData(
        @Path("id") id: Int
    ): Response<DetailResponse>

    @POST("")
    @Multipart
    suspend fun addAppeal(

    ): Response<AddAppealResponse>

    @GET("")
    suspend fun searchAppealType(
        //@Query("search") query: String = ""
    ): Response<SearchResponse>

    @GET("")
    @JvmSuppressWildcards
    suspend fun searchAppeal(
        @QueryMap map: Map<String, Any>
    ): Response<FilterResponse>

    @FormUrlEncoded
    @PUT("")
    suspend fun updateAppeal(
 //
    ): Response<AddAppealResponse>

    @DELETE("")
    suspend fun deleteImage(
        @Path("id") id: Int,
    ): Response<AddAppealResponse>

    @PUT("")
    @Multipart
    suspend fun updateImage(
        @Path("id") id: Int,
        @Part image: MultipartBody.Part
    ): Response<AddAppealResponse>

    @POST("")
    @Multipart
    suspend fun addImagesToAppeal(
        @Path("id") id: Int,
        @Part images: List<MultipartBody.Part>
    ): Response<AddAppealResponse>
}