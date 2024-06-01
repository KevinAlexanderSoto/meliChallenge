package com.kalex.melichallenge.search.model.repository

import com.kalex.melichallenge.commons.FlowStatus
import com.kalex.melichallenge.search.model.dto.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
interface SearchRepository {

    suspend fun searchItem(query: String): Flow<FlowStatus<List<Result>>>
}