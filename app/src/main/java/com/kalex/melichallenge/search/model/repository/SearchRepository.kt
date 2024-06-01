package com.kalex.melichallenge.search.model.repository

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
interface SearchRepository {

    suspend fun searchItem(query: String)