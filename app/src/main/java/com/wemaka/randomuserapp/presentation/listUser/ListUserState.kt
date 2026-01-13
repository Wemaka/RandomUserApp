package com.wemaka.randomuserapp.presentation.listUser

import com.wemaka.randomuserapp.data.model.User

data class ListUserState(
    val usersList: List<User> = emptyList()
)