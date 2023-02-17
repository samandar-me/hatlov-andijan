package com.sdk.domain.use_case.main

import com.sdk.domain.model.upload.AddAppealRequest
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias AddAppealBaseUseCase = BaseUseCase<AddAppealRequest, Flow<Status<Boolean>>>

class AddAppealUseCase @Inject constructor(
    private val repository: MainRepository
) : AddAppealBaseUseCase {
    override suspend fun invoke(params: AddAppealRequest): Flow<Status<Boolean>> {
        return repository.addAppealRequest(params)
    }
}