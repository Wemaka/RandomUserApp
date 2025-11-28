package com.wemaka.randomuserapp.presentation.detailUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wemaka.randomuserapp.domain.usecase.GetUser
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val userId: Int,
    private val getUser: GetUser
) : ViewModel() {
    var state by mutableStateOf(DetailUserState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                user = getUser(userId)
            )
        }
    }
}