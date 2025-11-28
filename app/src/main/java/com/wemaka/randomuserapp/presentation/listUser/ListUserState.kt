package com.wemaka.randomuserapp.presentation.listUser

import com.wemaka.randomuserapp.domain.entity.UserEntity

data class ListUserState(
    val usersList: List<UserEntity> = emptyList()
)