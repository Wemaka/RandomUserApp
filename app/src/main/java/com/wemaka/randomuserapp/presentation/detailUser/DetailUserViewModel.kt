package com.wemaka.randomuserapp.presentation.detailUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemaka.randomuserapp.domain.usecase.GetUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val userId: Int,
    private val getUser: GetUser
) : ViewModel() {
    private val _state = MutableStateFlow(DetailUserState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(user = getUser(userId))
            }
        }
    }
}