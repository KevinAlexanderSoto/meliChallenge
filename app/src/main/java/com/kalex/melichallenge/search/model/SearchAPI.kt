package com.kalex.melichallenge.search.model

import com.kalex.melichallenge.search.model.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
interface SearchAPI {
    @GET("sites/MLA/search")
    suspend fun searchItem( @Query("q") query: String) : SearchResponse

}