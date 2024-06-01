package com.kalex.melichallenge.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalex.melichallenge.commons.FlowStatus
import com.kalex.melichallenge.commons.ViewModelUiState
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.search.model.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
class SearchViewModel(
    private val searchRepository: SearchRepository,
    val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchState = MutableStateFlow<ViewModelUiState<List<Result>>>(
        ViewModelUiState.Loading(true),
    )
    val searchState: StateFlow<ViewModelUiState<List<Result>>>
        get() = _searchState

    fun searchProduct(query: String = " ") {
        viewModelScope.launch(dispatcher) {
            searchRepository.searchItem(query).collect { state ->
                when (state) {
                    is FlowStatus.Error -> _searchState.update { ViewModelUiState.Error(state.exception) }
                    is FlowStatus.Loading -> _searchState.update { ViewModelUiState.Loading(true) }
                    is FlowStatus.Success -> _searchState.update { ViewModelUiState.Success(state.data) }
                }
            }

        }
    }

}