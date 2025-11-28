package com.wemaka.randomuserapp.presentation.createUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemaka.randomuserapp.domain.usecase.AddUser
import com.wemaka.randomuserapp.domain.usecase.GetRandomUsers
import com.wemaka.randomuserapp.domain.util.Resource
import com.wemaka.randomuserapp.presentation.createUser.UiEvent.ShowSnackbar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val addUser: AddUser,
    private val getRandomUsers: GetRandomUsers
) : ViewModel() {
    var state by mutableStateOf(CreateUserState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onAction(action: CreateUserAction) {
        when (action) {
            is CreateUserAction.GenerateUser -> {
                viewModelScope.launch {
                    val result = getRandomUsers(
                        gender = state.gender,
                        nat = state.nat
                    )

                    when (result) {
                        is Resource.Success -> {
                            val users = result.data!!.users
                            addUser(users[0])

                            _eventFlow.emit(UiEvent.CreateUser)
                        }

                        is Resource.Error -> {
                            _eventFlow.emit(ShowSnackbar(result.message!!))
                        }
                    }
                }
            }

            is CreateUserAction.SelectGender -> {
                state = state.copy(gender = action.gender)
            }

            is CreateUserAction.SelectNat -> {
                state = state.copy(nat = action.nat)
            }
        }
    }
}

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    object CreateUser : UiEvent()
}