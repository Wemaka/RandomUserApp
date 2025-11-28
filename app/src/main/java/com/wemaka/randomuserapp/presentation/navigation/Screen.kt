package com.wemaka.randomuserapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
    @Serializable
    data object CreateUser : Screen

    @Serializable
    data class DetailUser(val id: Int) : Screen

    @Serializable
    data object ListUser : Screen
}