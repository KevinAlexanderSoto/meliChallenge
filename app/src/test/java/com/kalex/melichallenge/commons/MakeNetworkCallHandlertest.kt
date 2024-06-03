package com.kalex.melichallenge.commons

import app.cash.turbine.test
import com.kalex.melichallenge.CoroutineRule
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * @author kevin Alexander Soto on 6/2/2024
 * **/
class MakeNetworkCallHandlerTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = CoroutineRule()
    private suspend fun successfulCall(): String {
        return "Success Data"
    }

    private suspend fun unknownHostExceptionCall(): String {
        throw UnknownHostException("No internet connection")
    }

    private suspend fun httpExceptionCall(): String {
        throw HttpException(Response.error<ResponseBody>( 401,ResponseBody.create("plain/text".toMediaTypeOrNull(),"some content")))
    }

    private suspend fun generalException(): String {
        throw Exception("Something went wrong")
    }

    @Test
    fun `test successful network call`() = runBlocking {
        val flow = makeNetworkCallHandler { successfulCall() }
        // Assert that the flow emits Loading, Success, and completes
        flow.test {
            val loading = awaitItem()
            assertTrue(loading is FlowStatus.Loading)
            val success = awaitItem()
            assertTrue(success is FlowStatus.Success)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `test unknown host exception network call`() = runBlocking {
        val flow = makeNetworkCallHandler { unknownHostExceptionCall() }
        // Assert that the flow emits Loading, Success, and completes
        flow.test {
            val loading = awaitItem()
            assertTrue(loading is FlowStatus.Loading)
            val error = awaitItem()
            assertTrue(error is FlowStatus.Error)
            cancelAndConsumeRemainingEvents()
        }
        flow.collectLatest { result ->
            when (result) {
                is FlowStatus.Error -> {
                    assertTrue(result.exception == ErrorType.INTERNET_ERROR)
                }

                is FlowStatus.Loading -> {}
                is FlowStatus.Success -> TODO()
            }
        }
    }

    @Test
    fun `test HttpException network call`() = runBlocking {
        val flow = makeNetworkCallHandler { httpExceptionCall() }
        // Assert that the flow emits Loading, Success, and completes
        flow.test {
            val loading = awaitItem()
            assertTrue(loading is FlowStatus.Loading)
            val error = awaitItem()
            assertTrue(error is FlowStatus.Error)
            cancelAndConsumeRemainingEvents()
        }
        flow.collectLatest { result ->
            when (result) {
                is FlowStatus.Error -> {
                    assertTrue(result.exception == ErrorType.AUTHENTICATION_ERROR)
                }

                is FlowStatus.Loading -> {}
                is FlowStatus.Success -> TODO()
            }
        }
    }

    @Test
    fun `test GeneralException network call`() = runBlocking {
        val flow = makeNetworkCallHandler { generalException() }
        // Assert that the flow emits Loading, Success, and completes
        flow.test {
            val loading = awaitItem()
            assertTrue(loading is FlowStatus.Loading)
            val error = awaitItem()
            assertTrue(error is FlowStatus.Error)
            cancelAndConsumeRemainingEvents()
        }
        flow.collectLatest { result ->
            when (result) {
                is FlowStatus.Error -> {
                    assertTrue(result.exception == ErrorType.UNKNOWN_ERROR)
                }

                is FlowStatus.Loading -> {}
                is FlowStatus.Success -> TODO()
            }
        }
    }
}