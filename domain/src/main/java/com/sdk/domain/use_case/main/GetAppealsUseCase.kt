package com.sdk.domain.use_case.main

import com.sdk.domain.model.appeal.AppealResponse
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetAppealsBaseUseCase = BaseUseCase<Unit, Flow<Status<AppealResponse>>>

class GetAppealsUseCase @Inject constructor(
    private val repository: MainRepository
): GetAppealsBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Status<AppealResponse>> {
        return repository.getAppeals()
    }
}