package com.kalex.melichallenge.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalex.melichallenge.commons.ErrorType
import com.kalex.melichallenge.commons.FlowStatus
import com.kalex.melichallenge.commons.ViewModelUiState
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.search.model.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
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
        get() = _searchState.asStateFlow()

    private val _searchText = MutableStateFlow(" ")
    val searchText = _searchText.asStateFlow()

    fun searchProduct(query: String = " ") {
        viewModelScope.launch(dispatcher) {
            searchRepository.searchItem(query)
                .catch {
                    _searchState.update { ViewModelUiState.Error(ErrorType.UNKNOWN_ERROR) }
                }
                .collectLatest { state ->
                when (state) {
                    is FlowStatus.Error -> _searchState.update { ViewModelUiState.Error(state.exception) }
                    is FlowStatus.Loading -> _searchState.update { ViewModelUiState.Loading(true) }
                    is FlowStatus.Success -> _searchState.update {
                        if (state.data.isEmpty()) {
                            ViewModelUiState.Empty(true)
                        } else {
                            ViewModelUiState.Success(state.data)
                        }
                    }
                }
            }

        }
    }
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}