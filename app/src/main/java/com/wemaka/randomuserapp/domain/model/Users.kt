package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Users(
    @field:Json(name = "info")
    val info: Info,
    @field:Json(name = "results")
    val users: List<User>
)