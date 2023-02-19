package com.sdk.domain.use_case.main

import com.sdk.domain.model.VariableStatus
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetVariableBaseUseCase = BaseUseCase<Unit, Flow<Status<List<VariableStatus>>>>

class GetVariableUseCase @Inject constructor(
    private val mainRepository: MainRepository
): GetVariableBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Status<List<VariableStatus>>> {
        return mainRepository.getVariables()
    }
}