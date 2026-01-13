package com.wemaka.randomuserapp.presentation.detailUser

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.wemaka.randomuserapp.R
import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.model.fullName
import com.wemaka.randomuserapp.domain.model.InfoItem
import com.wemaka.randomuserapp.presentation.detailUser.component.InfoTabContent
import com.wemaka.randomuserapp.presentation.ui.theme.RandomUserAppTheme
import com.wemaka.randomuserapp.presentation.util.Nationality
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailUserScreen(
    onBack: () -> Unit,
    viewModel: DetailUserViewModel = koinViewModel()
) {
    DetailUserContent(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUserContent(
    state: DetailUserState,
    onBack: () -> Unit
) {
    val avatarSize = 120.dp
    val focusManager = LocalFocusManager.current
    var selectionEnabled by remember { mutableStateOf(true) }

    BackHandler(enabled = selectionEnabled) {
        selectionEnabled = false
        focusManager.clearFocus()
    }

    DisposableEffect(Unit) {
        onDispose {
            focusManager.clearFocus()
            selectionEnabled = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        selectionEnabled = false
                        focusManager.clearFocus()
                    }
                )
            },
    ) {
        GradientTopAppBar(
            avatarSize = avatarSize,
            pictureUrl = state.user?.pictureUrl,
            name = state.user?.fullName,
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(avatarSize / 2 + 26.dp))

        CardUserInfo(
            user = state.user
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradientTopAppBar(
    avatarSize: Dp,
    pictureUrl: String?,
    name: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isError by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
    ) {
        MediumTopAppBar(
            modifier = Modifier.padding(start = 14.dp),
            title = {},
            navigationIcon = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = MaterialTheme.colorScheme.primary
            )
        )

        IconButton(
            onClick = {
                focusManager.clearFocus()
                onBack()
            },
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape
                )
                .align(Alignment.TopStart)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_ios),
                contentDescription = "Back"
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = avatarSize / 2)
        ) {
            SubcomposeAsyncImage(
                model = pictureUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp)
                    )
                },
                onSuccess = { isError = false },
                error = {
                    Image(
                        painter = painterResource(R.drawable.ic_outline_image),
                        contentDescription = "Error loading",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(12.dp)
                    )
                },
                onError = { isError = true },
                modifier = Modifier
                    .size(avatarSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .then(
                        if (isError) {
                            Modifier.border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = avatarSize / 2 + 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hi how are you today?",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "I`m",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = name.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CardUserInfo(
    user: User?
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs =
        listOf(R.drawable.ic_person, R.drawable.ic_call, R.drawable.ic_alternate_email, R.drawable.ic_location_on)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .navigationBarsPadding(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        SecondaryTabRow(
            modifier = Modifier
                .fillMaxWidth(),
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex, matchContentSize = false),
                    color = MaterialTheme.colorScheme.secondary,
                    height = 2.dp
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index
                val cornerSize = MaterialTheme.shapes.large.topStart

                Tab(
                    modifier = Modifier.background(
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.surface
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(
                            topStart = cornerSize,
                            topEnd = cornerSize,
                            bottomStart = CornerSize(0),
                            bottomEnd = CornerSize(0)
                        )
                    ),
                    selected = isSelected,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Icon(
                            painterResource(title),
                            contentDescription = null
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        when (selectedTabIndex) {
            0 -> UserTabContent(user)
            1 -> PhoneTabContent(user)
            2 -> MailTabContent(user)
            3 -> LocationTabContent(user)
        }
    }
}

@Composable
fun UserTabContent(user: User?) {
    InfoTabContent(
        items = listOf(
            InfoItem("First name", user?.first ?: "-"),
            InfoItem("Last name", user?.last ?: "-"),
            InfoItem("Gender", user?.gender ?: "-"),
            InfoItem("Age", (user?.age ?: "-").toString()),
            InfoItem("Date of birth", user?.dateOfBirth?.take(10) ?: "-"),
            InfoItem(
                title = "Nationality",
                content = Nationality.fromCode(user?.nat.toString())?.fullName ?: "-",
                iconRes = Nationality.fromCode(user?.nat.toString())?.flagRes
            )
        )
    )
}

@Composable
fun PhoneTabContent(user: User?) {
    InfoTabContent(
        items = listOf(
            InfoItem("Home Phone", user?.homePhone ?: "-"),
            InfoItem("Cell Phone", user?.cellPhone ?: "—")
        )
    )
}

@Composable
fun MailTabContent(user: User?) {
    InfoTabContent(
        items = listOf(
            InfoItem("Email", user?.email ?: "-")
        )
    )
}

@Composable
fun LocationTabContent(user: User?) {
    InfoTabContent(
        items = listOf(
            InfoItem("Street number", (user?.streetNumber ?: "-").toString()),
            InfoItem("Street name", (user?.streetName ?: "-").toString()),
            InfoItem("City", (user?.city ?: "-").toString()),
            InfoItem("State", (user?.state ?: "-").toString()),
            InfoItem("Country", (user?.country ?: "-").toString()),
            InfoItem("Postcode", (user?.postcode ?: "-").toString())
        )
    )
}

@Preview
@Composable
fun DetailUserContentPreview() {
    RandomUserAppTheme {
        DetailUserContent(
            onBack = {},
            state = DetailUserState(null)
        )
    }
}