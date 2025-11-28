package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Login(
    @field:Json(name = "md5")
    val md5: String,
    @field:Json(name = "password")
    val password: String,
    @field:Json(name = "salt")
    val salt: String,
    @field:Json(name = "sha1")
    val sha1: String,
    @field:Json(name = "sha256")
    val sha256: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "uuid")
    val uuid: String
)