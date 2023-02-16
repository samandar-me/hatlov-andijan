package com.sdk.domain.use_case.main

import com.sdk.domain.model.LoginData
import com.sdk.domain.repository.PersistenceRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias SaveTokenBaseUseCase = BaseUseCase<Triple<LoginData, String, Int>, Unit>

class SaveTokenUseCase @Inject constructor(
    private val persistenceRepository: PersistenceRepository
): SaveTokenBaseUseCase {
    override suspend fun invoke(params: Triple<LoginData, String, Int>) {
        persistenceRepository.saveUserData(params.first, params.second, params.third)
    }
}