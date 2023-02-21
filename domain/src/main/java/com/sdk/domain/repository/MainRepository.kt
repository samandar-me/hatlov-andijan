package com.sdk.domain.repository

import com.sdk.domain.model.VariableStatus
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.domain.model.detail.DetailResponse
import com.sdk.domain.model.search.SearchData
import com.sdk.domain.model.update.UpdateAppealRequest
import com.sdk.domain.model.upload.AddAppealRequest
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MainRepository {
    suspend fun getVariables(): Flow<Status<List<VariableStatus>>>
    suspend fun getAppeals(): Flow<Status<AppealResponse>>
    suspend fun getDetailImages(id: Int): Flow<Status<DetailResponse>>
    suspend fun addAppealRequest(appealRequest: AddAppealRequest): Flow<Status<Boolean>>

    suspend fun searchAppealType(query: String): Flow<List<SearchData>>
    suspend fun searchAppealDashboard(query: String): Flow<Status<List<Murojaatlar>>>

    suspend fun updateAppeal(appealRequest: UpdateAppealRequest): Flow<Status<Boolean>>

    suspend fun deleteImage(id: Int)
}