package com.kalex.melichallenge.di

import com.kalex.melichallenge.search.model.SearchAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

val appModule = module {
    //to provide a singleton dependency
    single {
        Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchAPI::class.java)
    }

    single {
        Dispatchers.IO
    }

}