package com.kalex.melichallenge.search.model.repository

import com.kalex.melichallenge.commons.FlowStatus
import com.kalex.melichallenge.commons.makeNetworkCallHandler
import com.kalex.melichallenge.search.model.SearchAPI
import com.kalex.melichallenge.search.model.dto.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
class SearchRepositoryImpl(
    private val searchAPI: SearchAPI
): SearchRepository {
    override suspend fun searchItem(query: String): Flow<FlowStatus<List<Result>>> {
        return makeNetworkCallHandler {
            searchAPI.searchItem(query).results
        }
    }

}