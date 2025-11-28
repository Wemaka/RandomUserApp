package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Street(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "number")
    val number: Int
)