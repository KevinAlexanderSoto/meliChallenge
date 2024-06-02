package com.kalex.melichallenge.search.presentation.searchResult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kalex.melichallenge.search.model.dto.Result

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/
@Composable
fun SearchResultList(response: List<Result>?) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        response?.forEach { item ->
            Text(text = item.title)
        }
    }

}