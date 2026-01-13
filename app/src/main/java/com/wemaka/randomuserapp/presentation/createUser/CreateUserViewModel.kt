package com.wemaka.randomuserapp.presentation.createUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemaka.randomuserapp.data.util.Resource
import com.wemaka.randomuserapp.domain.usecase.AddUser
import com.wemaka.randomuserapp.domain.usecase.CreateRandomUsers
import com.wemaka.randomuserapp.presentation.createUser.UiEvent.ShowSnackbar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val addUser: AddUser,
    private val createRandomUsers: CreateRandomUsers
) : ViewModel() {
    private val _state = MutableStateFlow(CreateUserState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onAction(action: CreateUserAction) {
        when (action) {
            is CreateUserAction.GenerateUser -> {
                viewModelScope.launch {
                    val result = createRandomUsers(
                        gender = _state.value.gender,
                        nat = _state.value.nat
                    )

                    when (result) {
                        is Resource.Success -> {
                            val users = result.data!!
                            users.forEach { addUser(it) }

                            _eventFlow.emit(UiEvent.CreateUser)
                        }

                        is Resource.Error -> {
                            _eventFlow.emit(ShowSnackbar(result.message!!))
                        }
                    }
                }
            }

            is CreateUserAction.SelectGender -> {
                _state.update {
                    it.copy(gender = action.gender)
                }
            }

            is CreateUserAction.SelectNat -> {
                _state.update {
                    it.copy(nat = action.nat)
                }
            }
        }
    }
}

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    object CreateUser : UiEvent()
}