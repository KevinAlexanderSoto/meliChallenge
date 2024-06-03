package com.kalex.melichallenge.search.presentation

import app.cash.turbine.test
import com.kalex.melichallenge.CoroutineRule
import com.kalex.melichallenge.commons.FlowStatus
import com.kalex.melichallenge.commons.ViewModelUiState
import com.kalex.melichallenge.mocks.mockResult
import com.kalex.melichallenge.mocks.mockSearchResponse
import com.kalex.melichallenge.search.model.SearchAPI
import com.kalex.melichallenge.search.model.repository.SearchRepository
import com.kalex.melichallenge.search.model.repository.SearchRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author kevin Alexander Soto on 6/3/2024
 */
class SearchViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    private lateinit var searchViewModel: SearchViewModel
    @MockK
    private lateinit var mockSearchRepository: SearchRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        mockSearchRepository = mockk<SearchRepository>()
        searchViewModel = SearchViewModel(mockSearchRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun `initial state should be Loading`(): Unit = runBlocking {

        coEvery {  mockSearchRepository.searchItem("test_query") } returns flow{
            emit(  FlowStatus.Success(listOf(mockResult) ))
        }
        searchViewModel.searchState.test {
            val loading = awaitItem()
            TestCase.assertTrue(loading is ViewModelUiState.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
    @Test
    fun `successful empty Search product return ViewModelUiState Empty`(): Unit = runBlocking {

        coEvery {  mockSearchRepository.searchItem("test_query") } returns flow{
          emit(  FlowStatus.Success(listOf() ))
        }

        searchViewModel.searchProduct("test_query")
        searchViewModel.searchState.test {
            val loading = awaitItem()
            assertTrue(loading is ViewModelUiState.Empty)
            cancelAndConsumeRemainingEvents()
        }

    }

    @Test
    fun `error Search product return ViewModelUiState Error`(): Unit = runBlocking {

        coEvery {  mockSearchRepository.searchItem("test_query") } returns flow{
            emit(  throw Exception("Something went wrong") )
        }

        searchViewModel.searchProduct("test_query")
        searchViewModel.searchState.test {
            val error = awaitItem()
            assertTrue(error is ViewModelUiState.Error)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `successful Search product return ViewModelUiState Success`(): Unit = runBlocking {

        coEvery {  mockSearchRepository.searchItem("test_query") } returns flow{
            emit(  FlowStatus.Success(listOf(mockResult) ))
        }

        searchViewModel.searchProduct("test_query")
        searchViewModel.searchState.test {
            val loading = awaitItem()
            assertTrue(loading is ViewModelUiState.Success)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should update _searchText on onSearchTextChange`(): Unit = runBlocking {

        searchViewModel.onSearchTextChange("test_query")
        searchViewModel.searchText.test {
            val updated = awaitItem()
            assertTrue(updated ==  "test_query")
            cancelAndConsumeRemainingEvents()
        }
    }
}