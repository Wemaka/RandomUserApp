package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Coordinates(
    @field:Json(name = "latitude")
    val latitude: String,
    @field:Json(name = "longitude")
    val longitude: String
)