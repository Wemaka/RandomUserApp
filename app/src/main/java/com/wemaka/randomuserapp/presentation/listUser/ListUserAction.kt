package com.wemaka.randomuserapp.presentation.listUser

import com.wemaka.randomuserapp.domain.entity.UserEntity

sealed interface ListUserAction {
    data class DeleteUser(val user: UserEntity) : ListUserAction
    object RestoreUser : ListUserAction
}