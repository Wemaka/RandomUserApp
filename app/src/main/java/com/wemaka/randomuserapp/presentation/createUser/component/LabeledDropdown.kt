package com.wemaka.randomuserapp.presentation.createUser.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LabeledDropdown(
    label: String,
    hint: String,
    items: List<String>,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit,
    value: String = ""
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicDropdownMenu(
            hint = hint,
            content = items,
            onSelect = onSelect,
            value = value
        )
    }
}