package com.kalex.melichallenge.commons


import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException

/**
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

enum class ErrorType {
    INTERNET_ERROR,
    AUTHENTICATION_ERROR,
    UNKNOWN_ERROR,
}

sealed class ViewModelUiState<T> {
    data class Success<T>(val data: T) : ViewModelUiState<T>()
    data class Loading<T>(val isLoading: Boolean) : ViewModelUiState<T>()
    data class Error<T>(val exception: ErrorType) : ViewModelUiState<T>()
}

sealed class FlowStatus<T> {
    data class Success<T>(val data: T) : FlowStatus<T>()
    data class Error<T>(val exception: ErrorType) : FlowStatus<T>()
    data class Loading<T>(val message: String) : FlowStatus<T>()
}