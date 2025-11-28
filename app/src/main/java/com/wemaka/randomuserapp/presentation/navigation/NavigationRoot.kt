package com.wemaka.randomuserapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.wemaka.randomuserapp.presentation.createUser.CreateUserScreen
import com.wemaka.randomuserapp.presentation.detailUser.DetailUserScreen
import com.wemaka.randomuserapp.presentation.listUser.ListUserScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Screen.ListUser)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is Screen.ListUser -> {
                    NavEntry(key) {
                        ListUserScreen(
                            onUserClick = { userId ->
                                backStack.add(Screen.DetailUser(userId))
                            },
                            onFloatingActionClick = {
                                backStack.add(Screen.CreateUser)
                            }
                        )
                    }
                }

                is Screen.CreateUser -> {
                    NavEntry(key) {
                        CreateUserScreen(
                            onBack = { backStack.removeAt(backStack.lastIndex) }
                        )
                    }
                }

                is Screen.DetailUser -> {
                    NavEntry(key) {
                        DetailUserScreen(
                            onBack = { backStack.removeAt(backStack.lastIndex) },
                            viewModel = koinViewModel {
                                parametersOf(key.id)
                            }
                        )
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}