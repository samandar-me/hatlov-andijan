package com.sdk.domain.use_case.base

interface BaseUseCase<in Params, out Result> {
    suspend operator fun invoke(params: Params): Result
}