package com.sdk.data.repository

import com.sdk.data.mapper.toVariableData
import com.sdk.data.network.main.MainService
import com.sdk.domain.model.DataVariable
import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.model.detail.DetailImage
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
): MainRepository {
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

    override suspend fun getDetailImages(id: Int): Flow<Status<List<DetailImage>>> = flow {
        emit(Status.Loading)
        try {
            val response = mainService.getDetailImages(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Status.Success(it.images))
                }
            }
        } catch (e: Exception) {
            emit(Status.Error(e.toString()))
        }
    }
}