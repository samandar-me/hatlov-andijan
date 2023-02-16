package com.sdk.domain.use_case.main

import com.sdk.domain.model.DataVariable
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetVariableBaseUseCase = BaseUseCase<Unit, Flow<Status<DataVariable>>>

class GetVariableUseCase @Inject constructor(
    private val mainRepository: MainRepository
): GetVariableBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Status<DataVariable>> {
        return mainRepository.getVariables()
    }
}