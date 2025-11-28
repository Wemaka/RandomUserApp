package com.wemaka.randomuserapp.presentation.detailUser.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TabInfoRow(
    title: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$title: ",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = desc,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}