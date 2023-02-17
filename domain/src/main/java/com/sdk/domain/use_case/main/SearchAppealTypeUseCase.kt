package com.sdk.domain.use_case.main

import com.sdk.domain.model.search.SearchData
import com.sdk.domain.repository.MainRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias SearchAppealTypeBaseUseCase = BaseUseCase<String, Flow<List<SearchData>>>

class SearchAppealTypeUseCase @Inject constructor(
    private val repository: MainRepository
): SearchAppealTypeBaseUseCase {
    override suspend fun invoke(params: String): Flow<List<SearchData>> {
        return repository.searchAppealType(params)
    }
}