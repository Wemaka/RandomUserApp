package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Id(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "value")
    val value: String
)