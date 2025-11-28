package com.wemaka.randomuserapp.presentation.listUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.usecase.AddUser
import com.wemaka.randomuserapp.domain.usecase.DeleteUser
import com.wemaka.randomuserapp.domain.usecase.GetUsers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListUserViewModel(
    private val getUsers: GetUsers,
    private val addUser: AddUser,
    private val deleteUser: DeleteUser
) : ViewModel() {
    var state by mutableStateOf(ListUserState())
        private set

    private var recentlyDeletedUser: UserEntity? = null

    init {
        getUsers()
            .onEach { users ->
                state = state.copy(
                    usersList = users
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ListUserAction) {
        when (action) {
            is ListUserAction.DeleteUser -> {
                viewModelScope.launch {
                    deleteUser(action.user)
                    recentlyDeletedUser = action.user
                }
            }

            is ListUserAction.RestoreUser -> {
                viewModelScope.launch {
                    addUser(recentlyDeletedUser ?: return@launch)
                    recentlyDeletedUser = null
                }
            }
        }
    }
}