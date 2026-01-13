package com.wemaka.randomuserapp.presentation.detailUser.component

import android.content.ClipData
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wemaka.randomuserapp.R
import com.wemaka.randomuserapp.domain.model.InfoItem
import com.wemaka.randomuserapp.presentation.ui.theme.RandomUserAppTheme
import kotlinx.coroutines.launch

@Composable
fun InfoTabContent(
    modifier: Modifier = Modifier,
    items: List<InfoItem>
) {
    val clipboardManager = LocalClipboard.current
    val scope = rememberCoroutineScope()
    val copyText = remember {
        items.joinToString("\n") {
            "${it.title}: ${it.content}"
        }
    }

    SelectionContainer {
        Column(
            modifier = modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.forEach { item ->
                InfoRow(
                    title = item.title,
                    content = item.content,
                    iconRes = item.iconRes
                )
            }

            if (items.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                clipboardManager.setClipEntry(ClipEntry(ClipData.newPlainText("User info", copyText)))
                            }
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_content_copy),
                            contentDescription = "Copy all",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    iconRes: Int? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                    append("$title: ")
                }
                append(content)
            },
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )

        iconRes?.let {
            Spacer(modifier = Modifier.width(4.dp))

            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(it),
                contentDescription = content,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun PreviewInfoTabContent() {
    RandomUserAppTheme {
        InfoTabContent(
            items = listOf(
                InfoItem(
                    title = "User name",
                    content = "name"
                )
            )
        )
    }
}
