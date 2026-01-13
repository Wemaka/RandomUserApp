package com.wemaka.randomuserapp.presentation.listUser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.wemaka.randomuserapp.R
import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.model.fullName
import com.wemaka.randomuserapp.presentation.listUser.component.MinimalDropdownMenu
import com.wemaka.randomuserapp.presentation.ui.theme.RandomUserAppTheme
import com.wemaka.randomuserapp.presentation.util.Nationality
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListUserScreen(
    onUserClick: (Int) -> Unit,
    onFloatingActionClick: () -> Unit,
    viewModel: ListUserViewModel = koinViewModel()
) {
    ListUserContent(
        onUserClick = onUserClick,
        onFloatingActionClick = onFloatingActionClick,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = viewModel::onAction
    )
}

@Composable
fun ListUserContent(
    onUserClick: (Int) -> Unit,
    onFloatingActionClick: () -> Unit,
    state: ListUserState,
    onAction: (ListUserAction) -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFloatingActionClick() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add user",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            LazyListCardUser(
                list = state.usersList,
                onDelete = {
                    onAction(ListUserAction.DeleteUser(it))

                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "User deleted",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            onAction(ListUserAction.RestoreUser)
                        }
                    }
                },
                onUserClick = onUserClick
            )

            if (state.usersList.isEmpty()) {
                Text(
                    text = "Users not found",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun LazyListCardUser(
    list: List<User>,
    onUserClick: (Int) -> Unit,
    onDelete: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(list) {
            CardUser(
                id = it.id!!,
                name = it.fullName,
                phone = it.homePhone,
                nat = it.nat,
                imageUrl = it.pictureUrl,
                onDelete = { onDelete(it) },
                onCardClick = onUserClick
            )
        }
    }
}

@Composable
fun CardUser(
    id: Int,
    name: String,
    phone: String,
    nat: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onCardClick: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = { onCardClick(id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp)
                    )
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.ic_outline_image),
                        contentDescription = "Error loading",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(12.dp)
                    )
                },
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surface)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 20.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = phone,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Nationality.fromCode(nat)?.let {
                            Image(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .scale(1.3f)
                                    .size(20.dp),
                                painter = painterResource(it.flagRes),
                                contentDescription = it.fullName,
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = nat,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                MinimalDropdownMenu(
                    onDelete = { onDelete() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 20.dp, y = (-8).dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ListUserContentPreview() {
    RandomUserAppTheme {
        ListUserContent(
            onUserClick = {},
            onFloatingActionClick = {},
            state = ListUserState(
                usersList = listOf(
                    User(
                        1,
                        "asl-fkj-i48",
                        "Name",
                        "",
                        "",
                        "email@gmail.com",
                        "Male",
                        "US",
                        "+1 123 456 67 89",
                        "",
                        20,
                        "",
                        1,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        1L
                    )
                )
            ),
            onAction = {}
        )
    }
}