package com.kalex.melichallenge.commons


import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * In summary, this function wraps a suspend function into a flow that emits FlowStatus objects, handling different types of exceptions and emitting error messages accordingly.
 * @author kevin Alexander Soto on 6/1/2024
 * **/
fun <T> makeNetworkCallHandler(
    call: suspend () -> T,
) = flow<FlowStatus<T>> {
    try {
        emit(FlowStatus.Loading(""))
        val data = call()
        emit(FlowStatus.Success(data))
    } catch (e: UnknownHostException) {
        emit(FlowStatus.Error(ErrorType.INTERNET_ERROR))
    } catch (e: HttpException) {
        val errorMessage = when (e.code()) {
            401 -> ErrorType.AUTHENTICATION_ERROR
            else -> ErrorType.UNKNOWN_ERROR
        }
        emit(FlowStatus.Error(errorMessage))
    } catch (e: Exception) {
        emit(FlowStatus.Error(ErrorType.UNKNOWN_ERROR))
    }
}


