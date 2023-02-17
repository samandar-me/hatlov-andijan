package com.sdk.domain.repository

import com.sdk.domain.model.DataVariable
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.detail.DetailImage
import com.sdk.domain.model.upload.AddAppealRequest
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getVariables(): Flow<Status<DataVariable>>
    suspend fun getAppeals(): Flow<Status<AppealResponse>>
    suspend fun getDetailImages(id: Int): Flow<List<DetailImage>?>
    suspend fun addAppealRequest(appealRequest: AddAppealRequest): Flow<Status<Boolean>>
}