package com.sdk.domain.use_case.main

import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias DeleteImageBaseUseCase = BaseUseCase<Int, Unit>

class DeleteImageUseCase @Inject constructor(
    private val mainRepository: MainRepository
): DeleteImageBaseUseCase {
    override suspend fun invoke(params: Int) {
        mainRepository.deleteImage(params)
    }
}