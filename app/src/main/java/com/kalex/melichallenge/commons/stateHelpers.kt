package com.kalex.melichallenge.commons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

@Composable
fun <T>HandleViewModelResourceState(
    viewModelState: State<ViewModelUiState<T>>,
    onSuccess: @Composable (T?) -> Unit,
    onLoading: @Composable () -> Unit,
    onError: @Composable (errorType: ErrorType) -> Unit,
    onEmpty: @Composable () -> Unit = {},

    ){
    when (val value = viewModelState.value) {
        is ViewModelUiState.Loading -> { onLoading() }
        is ViewModelUiState.Success -> { onSuccess(value.data) }
        is ViewModelUiState.Error -> {
            onError(value.exception)
        }
        is ViewModelUiState.Empty -> { onEmpty() }
    }
}

enum class ErrorType {
    INTERNET_ERROR,
    AUTHENTICATION_ERROR,
    UNKNOWN_ERROR,
}

sealed class ViewModelUiState<T> {
    data class Success<T>(val data: T) : ViewModelUiState<T>()
    data class Empty<T>(val isEmpty: Boolean) : ViewModelUiState<T>()
    data class Loading<T>(val isLoading: Boolean) : ViewModelUiState<T>()
    data class Error<T>(val exception: ErrorType) : ViewModelUiState<T>()
}

sealed class FlowStatus<T> {
    data class Success<T>(val data: T) : FlowStatus<T>()
    data class Error<T>(val exception: ErrorType) : FlowStatus<T>()
    data class Loading<T>(val message: String) : FlowStatus<T>()
}