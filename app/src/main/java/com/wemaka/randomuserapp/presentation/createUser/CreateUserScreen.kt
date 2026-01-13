package com.wemaka.randomuserapp.presentation.createUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wemaka.randomuserapp.R
import com.wemaka.randomuserapp.presentation.createUser.component.LabeledDropdown
import com.wemaka.randomuserapp.presentation.ui.theme.RandomUserAppTheme
import com.wemaka.randomuserapp.presentation.util.Gender
import com.wemaka.randomuserapp.presentation.util.Nationality
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateUserScreen(
    onBack: () -> Unit,
    viewModel: CreateUserViewModel = koinViewModel()
) {
    CreateUserContent(
        onBack = onBack,
        onAction = viewModel::onAction,
        event = viewModel.eventFlow,
        state = viewModel.state.collectAsStateWithLifecycle().value
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUserContent(
    onBack: () -> Unit,
    onAction: (CreateUserAction) -> Unit,
    event: SharedFlow<UiEvent>,
    state: CreateUserState
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        event.collectLatest {
            when (it) {
                is UiEvent.CreateUser -> {
                    onBack()
                }

                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(it.message)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Generate user",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_ios),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabeledDropdown(
                label = "Select Gender :",
                hint = "Gender",
                items = Gender.entries.map { it.name.lowercase() },
                onSelect = { onAction(CreateUserAction.SelectGender(it)) },
                value = state.gender
            )

            Spacer(modifier = Modifier.height(24.dp))

            LabeledDropdown(
                label = "Select Nationality :",
                hint = "Nationality",
                items = Nationality.getAllFullName(),
                onSelect = {
                    onAction(
                        CreateUserAction.SelectNat(
                            Nationality.fromName(it)?.code ?: ""
                        )
                    )
                },
                value = state.nat
            )

            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .weight(1f)
            )

            Button(
                onClick = {
                    onAction(CreateUserAction.GenerateUser)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "GENERATE",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateUserContentPreview() {
    RandomUserAppTheme {
        CreateUserContent(
            onBack = {},
            onAction = {},
            event = MutableSharedFlow(),
            state = CreateUserState("", "")
        )
    }
}