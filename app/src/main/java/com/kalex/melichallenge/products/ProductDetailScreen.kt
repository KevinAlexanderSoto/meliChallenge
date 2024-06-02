package com.kalex.melichallenge.products

import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.kalex.melichallenge.R
import com.kalex.melichallenge.commons.ErrorType
import com.kalex.melichallenge.commons.HandleViewModelResourceState
import com.kalex.melichallenge.commons.composables.KalexEmptyScreen
import com.kalex.melichallenge.commons.composables.KalexErrorScreen
import com.kalex.melichallenge.commons.composables.KalexLoadingIndicator
import com.kalex.melichallenge.navigation.NavigationViewModel
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.search.presentation.searchResult.SearchResultList
import org.koin.androidx.compose.koinViewModel

/**
 * @author kevin Alexander Soto on 6/2/2024
 * **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navigationViewModel: NavigationViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as ComponentActivity
    ),
){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.search_result_topBar_title)) },
                navigationIcon = {
                    IconButton(
                        onClick = {  },
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
            Text(text = navigationViewModel.getActualProduct()?.title ?: "NOT SAVED")
        }
    }
}