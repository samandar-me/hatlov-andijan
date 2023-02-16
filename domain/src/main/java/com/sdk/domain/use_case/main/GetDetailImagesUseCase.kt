package com.sdk.domain.use_case.main

import com.sdk.domain.model.detail.DetailImage
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetDetailImagesBaseUseCase = BaseUseCase<Int, Flow<List<DetailImage>?>>

class GetDetailImagesUseCase @Inject constructor(
    private val repository: MainRepository
): GetDetailImagesBaseUseCase {
    override suspend fun invoke(params: Int): Flow<List<DetailImage>?> {
        return repository.getDetailImages(params)
    }
}