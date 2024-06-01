package com.kalex.melichallenge.search.domain

import com.kalex.melichallenge.search.model.repository.SearchRepository

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
class GetSearchProductUseCaseImpl(
    private val searchRepository: SearchRepository
) : GetSearchProductUseCase {
// In this case the useCase is not necessary, but when we need business logic and similar we can use it.
}