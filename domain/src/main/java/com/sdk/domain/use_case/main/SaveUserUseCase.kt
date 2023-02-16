package com.sdk.domain.use_case.main

import com.sdk.domain.model.UserEntity
import com.sdk.domain.repository.PersistenceRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias SaveUserBaseUseCase = BaseUseCase<UserEntity, Unit>

class SaveUserUseCase @Inject constructor(
    private val repository: PersistenceRepository
): SaveUserBaseUseCase {
    override suspend fun invoke(params: UserEntity) {
        repository.saveUser(params)
    }
}