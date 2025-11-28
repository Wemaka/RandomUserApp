package com.wemaka.randomuserapp.presentation.listUser.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wemaka.randomuserapp.R

@Composable
fun MinimalDropdownMenu(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        IconButton(
            modifier = Modifier,
            onClick = { expanded = !expanded }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vert),
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {
                    onDelete()
                    expanded = false
                }
            )
        }
    }
}