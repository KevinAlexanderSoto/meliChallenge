package com.kalex.melichallenge.commons.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author kevin Alexander Soto on 6/1/2024
 * **/

@Composable
fun KalexErrorScreen(
    @StringRes rationaleText: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "No subject Icon",
            modifier = Modifier.size(80.dp),
        )
        Text(
            text = stringResource(rationaleText),
            fontSize = 20.sp,
        )

    }
}