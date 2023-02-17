package com.sdk.data.repository

import android.util.Log
import com.sdk.data.mapper.toVariableData
import com.sdk.data.network.main.MainService
import com.sdk.domain.model.DataVariable
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.detail.DetailImage
import com.sdk.domain.model.upload.AddAppealRequest
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {
    override suspend fun getVariables(): Flow<Status<DataVariable>> = flow {
        emit(Status.Loading)
        try {
            val response = mainService.getVariables()
            if (response.code() == 401) {
                emit(Status.Error("401"))
            } else {
                response.body().let {
                    if (it?.success!!) {
                        emit(Status.Success(it.toVariableData()))
                    }
                }
            }
        } catch (e: Exception) {
            emit(Status.Error(e.toString()))
        }
    }

    override suspend fun getAppeals(): Flow<Status<AppealResponse>> = flow {
        emit(Status.Loading)
        try {
            val response = mainService.getAppeals()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Status.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(Status.Error(e.toString()))
        }
    }

    override suspend fun addAppealRequest(appealRequest: AddAppealRequest): Flow<Status<Boolean>> =
        flow {
            emit(Status.Loading)
            try {
                val response = mainService.addAppeal(
                    appealRequest.address,
                    appealRequest.ownerHomeName,
                    appealRequest.ownerHomeYear,
                    appealRequest.ownerHomeGender,
                    appealRequest.ownerHomePhone,
                    appealRequest.ownerAsSpeaker,
                    appealRequest.speakerName,
                    appealRequest.speakerYear,
                    appealRequest.speakerGender,
                    appealRequest.speakerPhone,
                    appealRequest.type,
                    appealRequest.comment,
                    appealRequest.images.map {
                        MultipartBody.Part.createFormData(
                            "images",
                            it.name,
                            it.asRequestBody()
                        )
                    }
                )
                response.body()?.let {
                    if (it.success) {
                        emit(Status.Success(true))
                    }
                }
            } catch (e: Exception) {
                emit(Status.Error(e.toString()))
            }
        }

    override suspend fun getDetailImages(id: Int): Flow<List<DetailImage>?> = flow {
        val response = mainService.getDetailImages(id)
        Log.d("@@@", "getDetailImages: ${response.code()}")
        Log.d("@@@", "getDetailImages: ${response.body()}")
        Log.d("@@@", "getDetailImages: $id}")
        emit(response.body()?.detailData?.images)
    }
}
