package com.sdk.domain.use_case.main

import com.sdk.domain.model.UserEntity
import com.sdk.domain.repository.PersistenceRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetUserBaseUseCase = BaseUseCase<Int, Flow<UserEntity?>>

class GetUserUseCase @Inject constructor(
    private val repository: PersistenceRepository
): GetUserBaseUseCase {
    override suspend fun invoke(params: Int): Flow<UserEntity?> {
        return repository.getUser(params)
    }
}