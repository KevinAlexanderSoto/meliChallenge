package com.kalex.melichallenge.search.model.repository

import com.kalex.melichallenge.CoroutineRule
import com.kalex.melichallenge.commons.FlowStatus
import com.kalex.melichallenge.mocks.mockResult
import com.kalex.melichallenge.mocks.mockSearchResponse
import com.kalex.melichallenge.search.model.SearchAPI
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author kevin Alexander Soto on 6/2/2024
 */
class SearchRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    @MockK
    private lateinit var mockSearchAPI: SearchAPI

    private lateinit var searchRepository: SearchRepositoryImpl

    @Before
    fun setUp() {
        mockSearchAPI = mockk<SearchAPI>()
        searchRepository = SearchRepositoryImpl(mockSearchAPI)
    }

    @Test
    fun `successful Search Api call show return mockResult and success state`() = runBlocking {

        coEvery {  mockSearchAPI.searchItem("test_query") } returns mockSearchResponse

        val flow = searchRepository.searchItem("test_query")
        flow.collectLatest { result ->
            when(result){
                is FlowStatus.Error -> TODO()
                is FlowStatus.Loading -> {}
                is FlowStatus.Success -> {
                    assertTrue(mockResult == result.data[0])
                }
            }
        }
    }
}