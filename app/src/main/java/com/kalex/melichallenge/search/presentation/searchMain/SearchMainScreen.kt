package com.kalex.melichallenge.search.presentation.searchMain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.kalex.melichallenge.commons.composables.KalexSearchBar
import com.kalex.melichallenge.search.presentation.SearchViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

@Composable
fun SearchMainScreen(
    onSearchProduct: (query: String) -> Unit,
    searchViewModel: SearchViewModel = koinViewModel(),
) {
    val searchText by searchViewModel.searchText.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            KalexSearchBar(
                hint = "buscar",
                onSearchClicked = { onSearchProduct(searchText) },
                onTextChange = { searchViewModel.onSearchTextChange(it) }
            )

        }

    }

}
