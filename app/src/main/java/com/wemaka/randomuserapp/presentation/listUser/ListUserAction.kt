package com.wemaka.randomuserapp.presentation.listUser

import com.wemaka.randomuserapp.data.model.User

sealed interface ListUserAction {
    data class DeleteUser(val user: User) : ListUserAction
    object RestoreUser : ListUserAction
}