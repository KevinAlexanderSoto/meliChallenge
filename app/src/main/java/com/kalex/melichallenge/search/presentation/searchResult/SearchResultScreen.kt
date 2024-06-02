package com.kalex.melichallenge.search.presentation.searchResult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kalex.melichallenge.R
import com.kalex.melichallenge.commons.ErrorType
import com.kalex.melichallenge.commons.HandleViewModelResourceState
import com.kalex.melichallenge.commons.composables.KalexEmptyScreen
import com.kalex.melichallenge.commons.composables.KalexErrorScreen
import com.kalex.melichallenge.commons.composables.KalexLoadingIndicator
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.search.presentation.SearchViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    query: String,
    onGoToDetail: () -> Unit,
    onBackNavigation: () -> Unit,
    searchViewModel: SearchViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        searchViewModel.searchProduct(query)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.search_result_topBar_title)) },
                navigationIcon = {
                        IconButton(
                            onClick = { onBackNavigation() },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "go back",
                            )
                        }
                },
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            HandleViewModelResourceState(
                searchViewModel.searchState.collectAsState(),
                onEmpty = {
                    KalexEmptyScreen(
                        rationaleText = R.string.search_result_empty,
                    )
                },
                onLoading = { KalexLoadingIndicator() },
                onSuccess = { response: List<Result>? ->
                    SearchResultList(response){
                        onGoToDetail()
                    }
                },
                onError = { error ->
                    when (error) {
                        ErrorType.INTERNET_ERROR -> KalexErrorScreen(rationaleText = R.string.search_result_internet_error)
                        ErrorType.AUTHENTICATION_ERROR -> KalexErrorScreen(rationaleText = R.string.search_result_unknown_error)
                        ErrorType.UNKNOWN_ERROR -> KalexErrorScreen(rationaleText = R.string.search_result_unknown_error)
                    }
                },
            )
        }
    }
}