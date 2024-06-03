package com.kalex.melichallenge.search.presentation.searchMain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            KalexSearchBar(
                hint = "Buscar",
                onSearchClicked = { onSearchProduct(searchText) },
                onTextChange = { searchViewModel.onSearchTextChange(it) }
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = " ",
                modifier = Modifier.size(80.dp).padding(0.dp,12.dp),
            )
            Text(
                text = "Busca tus productos",
                fontSize = 20.sp,
            )

        }

    }

}
