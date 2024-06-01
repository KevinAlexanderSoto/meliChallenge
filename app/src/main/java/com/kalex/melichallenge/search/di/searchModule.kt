package com.kalex.melichallenge.search.di

import android.system.Os.bind
import com.kalex.melichallenge.search.domain.GetSearchProductUseCase
import com.kalex.melichallenge.search.model.repository.SearchRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

val searchModule = module {

    singleOf(::SearchRepositoryImpl){ bind<SearchRepository>() }
    factoryOf(::GetSearchProductUseCaseImpl){ bind<GetSearchProductUseCase>() }
}