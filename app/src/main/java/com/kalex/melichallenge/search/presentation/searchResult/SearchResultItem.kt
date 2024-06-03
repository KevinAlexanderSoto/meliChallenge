package com.kalex.melichallenge.search.presentation.searchResult

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kalex.melichallenge.ui.theme.MeliChallengeTheme
import com.kalex.melichallenge.ui.theme.Tertiary

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

@Composable
fun SearchResultItem(
    title: String,
    imageUrl: String,
    price: String,
    freeShipping: Boolean,
    mercadoPago: Boolean,
    availableQuantity: Int,
    onProductClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable { onProductClick() },
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .height(100.dp)
                .width(100.dp),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Text(
                    text = price,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "$availableQuantity disponibles", fontSize = 14.sp)
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(40.dp)
                        .padding(4.dp)
                ) {
                    item {
                        AnimatedVisibility(visible = freeShipping) {
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "Envio gratis",
                                fontSize = 11.sp,
                                color = Tertiary,
                            )
                        }
                    }
                    item {
                        AnimatedVisibility(visible = mercadoPago) {
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "Mercado Pago",
                                fontSize = 11.sp,
                                color = Tertiary,
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
fun SearchResultItemPreview() {
    MeliChallengeTheme {
        SearchResultItem(
            title = "titulo largo",
            imageUrl = "http://http2.mlstatic.com/D_735647-MLU75407666001_032024-I.jpg",
            price = "2121.3",
            freeShipping = true,
            mercadoPago = true,
            availableQuantity = 30
        ){

        }
    }
}
