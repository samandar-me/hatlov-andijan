package com.sdk.domain.use_case.main

import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias SearchAppealDashboardBaseUseCase = BaseUseCase<String, Flow<Status<List<Murojaatlar>>>>

class SearchAppealDashboardUseCase @Inject constructor(
    private val mainRepository: MainRepository
): SearchAppealDashboardBaseUseCase {
    override suspend fun invoke(params: String): Flow<Status<List<Murojaatlar>>> {
        return mainRepository.searchAppealDashboard(params)
    }
}