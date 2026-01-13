package com.wemaka.randomuserapp.presentation.listUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.domain.usecase.AddUser
import com.wemaka.randomuserapp.domain.usecase.DeleteUser
import com.wemaka.randomuserapp.domain.usecase.GetUsers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListUserViewModel(
    private val getUsers: GetUsers,
    private val addUser: AddUser,
    private val deleteUser: DeleteUser
) : ViewModel() {
    private val _state = MutableStateFlow(ListUserState())
    val state = _state.asStateFlow()

    private var recentlyDeletedUser: User? = null

    init {
        getUsers()
            .onEach { users ->
                _state.update {
                    it.copy(usersList = users)
                }
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