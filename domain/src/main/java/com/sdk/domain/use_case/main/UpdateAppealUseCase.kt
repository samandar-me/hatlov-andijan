
package com.sdk.domain.use_case.main

import com.sdk.domain.model.update.UpdateAppealRequest
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias UpdateAppealBaseUseCase = BaseUseCase<UpdateAppealRequest, Flow<Status<Boolean>>>

class UpdateAppealUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : UpdateAppealBaseUseCase {
    override suspend fun invoke(params: UpdateAppealRequest): Flow<Status<Boolean>> {
        return mainRepository.updateAppeal(params)
    }
}