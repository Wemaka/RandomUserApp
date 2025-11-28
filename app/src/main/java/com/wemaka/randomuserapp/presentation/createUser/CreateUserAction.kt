package com.wemaka.randomuserapp.presentation.createUser

sealed interface CreateUserAction {
    object GenerateUser : CreateUserAction
    data class SelectGender(val gender: String) : CreateUserAction
    data class SelectNat(val nat: String) : CreateUserAction
}