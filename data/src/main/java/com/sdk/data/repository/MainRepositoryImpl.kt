package com.sdk.data.repository

import android.util.Log
import com.sdk.data.network.main.MainService
import com.sdk.domain.model.VariableStatus
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.domain.model.detail.DetailResponse
import com.sdk.domain.model.search.SearchData
import com.sdk.domain.model.update.UpdateAppealRequest
import com.sdk.domain.model.upload.AddAppealRequest
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
) : MainRepository {
    private val TAG = "@@@"
    override suspend fun getVariables(): Flow<Status<List<VariableStatus>>> = flow {
        emit(Status.Loading)
        try {
            val response = mainService.getVariables()
            if (response.code() == 401) {
                emit(Status.Error("401"))
            } else {
                response.body().let {
                    if (it?.success!!) {
                        emit(Status.Success(it.data.status))
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
                Log.d("@@@", "addAppealRequest: $appealRequest")
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

    override suspend fun getDetailImages(id: Int): Flow<Status<DetailResponse>> = flow {
        try {
            emit(Status.Loading)
            val response = mainService.getDetailData(id)
            response.body()?.let {
                if (it.success) {
                    emit(Status.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(Status.Error(e.toString()))
        }
    }

    override suspend fun searchAppealType(query: String): Flow<List<SearchData>> = flow {
        try {
            val response = mainService.searchAppealType(query)
            response.body()?.let {
                if (it.success) {
                    emit(it.data)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun searchAppealDashboard(query: String): Flow<Status<List<Murojaatlar>>> =
        flow {
            emit(Status.Loading)
            try {
                val mapQuery = mapOf("search" to query)
                val response = mainService.searchAppeal(mapQuery)
                response.body()?.let {
                    emit(Status.Success(it.data.result))
                }
            } catch (e: Exception) {
                emit(Status.Error(e.toString()))
            }
        }

    override suspend fun updateAppeal(appealRequest: UpdateAppealRequest): Flow<Status<Boolean>> =
        flow {
            emit(Status.Loading)
            try {
                val response = mainService.updateAppeal(
                    appealRequest.id,
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
                    appealRequest.comment
                )
                response.body().let {
                    if (it?.success == true) {
                        appealRequest.oldImages.forEach { image ->
                           mainService.deleteImage(image.id)
                        }
                        val addResponse = mainService.addImagesToAppeal(
                            appealRequest.id,
                            appealRequest.newImages.map {file ->
                                MultipartBody.Part.createFormData(
                                    "images",
                                    file.name,
                                    file.asRequestBody()
                                )
                            }
                        )
                        Log.d(TAG, "updateAppealF: ${addResponse.code()}")
                        Log.d(TAG, "updateAppealF: ${addResponse.body()}")
                        Log.d(TAG, "updateAppealF: ${addResponse.message()}")
                        if (addResponse.body()?.success == true) {
                            emit(Status.Success(true))
                        }
                    }
                }
            } catch (e: Exception) {
                emit(Status.Error(e.toString()))
            }
        }
}
