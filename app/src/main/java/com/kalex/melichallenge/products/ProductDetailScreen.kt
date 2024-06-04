package com.kalex.melichallenge.products

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kalex.melichallenge.R
import com.kalex.melichallenge.commons.FormatCurrencyUseCase
import com.kalex.melichallenge.commons.composables.KalexErrorScreen
import com.kalex.melichallenge.navigation.NavigationViewModel
import com.kalex.melichallenge.search.model.dto.Attribute
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.ui.theme.MeliChallengeTheme
import com.kalex.melichallenge.ui.theme.Tertiary
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
) {
    val product: Result? = remember {
        navigationViewModel.getActualProduct()
    }

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            if (product != null) {
                Product(product)
            }else {
                KalexErrorScreen(rationaleText = R.string.search_result_unknown_error)
            }
        }
    }
}

@Composable
fun Product(product: Result) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = product.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(4.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillBounds,
        )

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = FormatCurrencyUseCase.format(
                    value = product.price,
                    currency = product.currency_id
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
            )
            Text(
                text = product.condition ?: " ",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "${product.available_quantity} disponibles", fontSize = 14.sp)
            product.shipping?.let {
                AnimatedVisibility(visible = it.free_shipping) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Envio gratis",
                        fontSize = 12.sp,
                        color = Tertiary,
                    )
                }
            }
        }

        Text(
            modifier = Modifier.padding(8.dp),
            text = "Detalles",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Card(
            elevation = CardDefaults.cardElevation(4.dp, 4.dp, 4.dp, 4.dp, 4.dp, 4.dp),
            modifier = Modifier
                .fillMaxWidth(0.99f)
                .align(Alignment.CenterHorizontally)
        ) {
            product.attributes.forEach { attribute ->
                if (attribute.id != "GTIN") {
                    Row(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                            Text(
                                modifier = Modifier,
                                fontWeight = FontWeight.SemiBold,
                                text = attribute.name + ":",
                                overflow = TextOverflow.Clip,
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                overflow = TextOverflow.Ellipsis,
                                text = attribute.value_name ?: " "
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductPreview() {
    MeliChallengeTheme {
        Product(
            Result(
                accepts_mercadopago = true,
                attributes = listOf(
                    Attribute(
                        attribute_group_id = "AB123",
                        attribute_group_name = "Color",
                        id = "ATTR456",
                        name = "Color",
                        source = 12345,
                        value_id = "VAL789",
                        value_name = "Blue",
                        value_struct = null, // Can be null if not applicable
                        value_type = "string",
                        values = listOf()
                    )
                ),
                available_quantity = 10,
                buying_mode = "buy",
                catalog_listing = false,
                catalog_product_id = "MLA12345678",
                category_id = "MLA1051",  // Replace with appropriate category ID
                condition = "new",
                currency_id = "ARS",
                differential_pricing = null,  // Optional
                discounts = Any(),  // Can be any type, replace with actual discount information if available
                domain_id = "MLA",
                id = "MLA98765432",
                installments = null,  // Optional
                inventory_id = null,  // Can be any type, set to null if not available
                listing_type_id = "gold_premium",
                official_store_id = 1,  // Can be any type, set to null if not available
                order_backend = 0,
                original_price = 10.0,  // Can be any type, set to null if not available
                permalink = "https://articulo.mercadolibre.com.ar/MLA12345678",
                price = 1234.56,
                promotions = listOf( /* List of promotions objects */),  // Optional
                sale_price = 12.00,  // Can be any type, set to null if not available
                seller = null,  // Replace with seller information
                shipping = null,  // Replace with shipping details
                site_id = "MLA",
                stop_time = "2024-06-03T00:00:00.000Z",
                thumbnail = "https://i.mlcdn.com.ar/600x600/articulo/MLA12345678.jpg",
                thumbnail_id = "MLA12345678-I",
                title = "Awesome Product Name",
                use_thumbnail_id = true,
                variation_filters = emptyList(),  // Initialize as empty list
                variation_id = "",
                variations_data = null,  // Can be null if not applicable
                winner_item_id = null  // Can be any type, set to null if not available
            )
        )
    }
}