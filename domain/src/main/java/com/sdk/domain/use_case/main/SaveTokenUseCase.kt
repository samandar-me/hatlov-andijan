package com.sdk.domain.use_case.main

import com.sdk.domain.repository.PersistenceRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias SaveTokenBaseUseCase = BaseUseCase<Pair<String, Int>, Unit>

class SaveTokenUseCase @Inject constructor(
    private val persistenceRepository: PersistenceRepository
): SaveTokenBaseUseCase {
    override suspend fun invoke(params: Pair<String, Int>) {
        persistenceRepository.saveUserIdAndToken(params.first, params.second)
    }
}