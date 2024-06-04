package com.kalex.melichallenge.search.presentation.searchResult.compose

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kalex.melichallenge.commons.FormatCurrencyUseCase
import com.kalex.melichallenge.navigation.NavigationViewModel
import com.kalex.melichallenge.search.model.dto.Result
import org.koin.androidx.compose.koinViewModel

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
@Composable
fun SearchResultList(
    response: List<Result>?,
    navigationViewModel: NavigationViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as ComponentActivity
    ),
    onProductClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        response?.forEach { product ->
            item(key = product.id) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.size(2.dp))
                    SearchResultItem(
                        title = product.title,
                        imageUrl = product.thumbnail,
                        price = FormatCurrencyUseCase.format(
                            value = product.price,
                            currency = product.currency_id
                        ),
                        freeShipping = product.shipping?.free_shipping ?: false,
                        mercadoPago = product.accepts_mercadopago,
                        availableQuantity = product.available_quantity
                    ) {
                        navigationViewModel.setProductDetail(product)
                        onProductClick()
                    }
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(4.dp),
                    )
                }
            }
        }
    }
}