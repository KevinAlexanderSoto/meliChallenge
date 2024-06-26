package com.kalex.melichallenge.di

import com.kalex.melichallenge.MainActivity
import com.kalex.melichallenge.commons.FormatCurrencyUseCase
import com.kalex.melichallenge.navigation.NavigationViewModel
import com.kalex.melichallenge.search.model.SearchAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
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
    viewModelOf(::NavigationViewModel)
    scope<MainActivity>() {
        scoped { NavigationViewModel() }
    }

}