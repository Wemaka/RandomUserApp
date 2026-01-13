package com.wemaka.randomuserapp.data.data_source.remote.model


import com.squareup.moshi.Json

data class Name(
    @field:Json(name = "first")
    val first: String,
    @field:Json(name = "last")
    val last: String,
    @field:Json(name = "title")
    val title: String
)