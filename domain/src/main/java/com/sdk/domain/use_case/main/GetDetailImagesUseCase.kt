package com.sdk.domain.use_case.main

import com.sdk.domain.model.detail.DetailResponse
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetDetailImagesBaseUseCase = BaseUseCase<Int, Flow<Status<DetailResponse>>>

class GetDetailImagesUseCase @Inject constructor(
    private val repository: MainRepository
): GetDetailImagesBaseUseCase {
    override suspend fun invoke(params: Int): Flow<Status<DetailResponse>> {
        return repository.getDetailImages(params)
    }
}