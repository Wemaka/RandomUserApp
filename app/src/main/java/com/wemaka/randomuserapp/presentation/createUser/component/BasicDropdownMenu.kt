package com.wemaka.randomuserapp.presentation.createUser.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDropdownMenu(
    hint: String,
    modifier: Modifier = Modifier,
    content: List<String>,
    onSelect: (String) -> Unit,
    value: String = ""
) {
    var expanded by remember { mutableStateOf(false) }
    var valueTextField by remember { mutableStateOf(value) }

    ExposedDropdownMenuBox(
        modifier = modifier
            .wrapContentSize()
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.small),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = valueTextField,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column {
                content.forEachIndexed { index, value ->
                    Text(
                        text = value,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                            .clickable {
                                valueTextField = value
                                onSelect(value)
                                expanded = false
                            }
                    )

                    if (index != content.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewBasicDropdownMenu() {
    BasicDropdownMenu(
        hint = "Text",
        modifier = Modifier
            .size(400.dp)
            .background(Color.White),
        content = listOf("text 1", "text 2", "text 3"),
        onSelect = {}
    )
}