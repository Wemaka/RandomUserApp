package com.wemaka.randomuserapp.data.data_source.remote.model


import com.squareup.moshi.Json

data class UsersResponse(
    @field:Json(name = "info")
    val info: Info,
    @field:Json(name = "results")
    val remoteUsers: List<RemoteUser>
)