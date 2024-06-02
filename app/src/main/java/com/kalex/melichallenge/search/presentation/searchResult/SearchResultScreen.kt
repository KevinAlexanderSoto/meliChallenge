package com.kalex.melichallenge.search.presentation.searchResult

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kalex.melichallenge.R
import com.kalex.melichallenge.commons.HandleViewModelResourceState
import com.kalex.melichallenge.commons.composables.KalexEmptyScreen
import com.kalex.melichallenge.commons.composables.KalexLoadingIndicator
import com.kalex.melichallenge.search.presentation.SearchViewModel
import org.koin.androidx.compose.koinViewModel
import com.kalex.melichallenge.search.model.dto.Result

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
@Composable
fun SearchResultScreen(
    query: String,
    onGoToDetail: () -> Unit,
    searchViewModel: SearchViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        searchViewModel.searchProduct(query)
    }
    HandleViewModelResourceState(
        searchViewModel.searchState.collectAsState(),
        onEmpty = {
            KalexEmptyScreen(
                rationaleText = R.string.search_result_empty,
            )
        },
        onLoading = { KalexLoadingIndicator() },
        onSuccess = { response: List<Result>? ->
            SearchResultList(response)
        },
        onError = { it ->
            Text(text = "${it}")
        },
    )
}