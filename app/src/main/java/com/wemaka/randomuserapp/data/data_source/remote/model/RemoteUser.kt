package com.wemaka.randomuserapp.data.data_source.remote.model


import com.squareup.moshi.Json

data class RemoteUser(
    @field:Json(name = "cell")
    val cell: String,
    @field:Json(name = "dob")
    val dob: Dob,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "gender")
    val gender: String,
    @field:Json(name = "id")
    val id: Id,
    @field:Json(name = "location")
    val location: Location,
    @field:Json(name = "login")
    val login: Login,
    @field:Json(name = "name")
    val name: Name,
    @field:Json(name = "nat")
    val nat: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "picture")
    val picture: Picture,
    @field:Json(name = "registered")
    val registered: Registered
)