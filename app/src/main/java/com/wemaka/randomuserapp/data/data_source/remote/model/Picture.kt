package com.wemaka.randomuserapp.data.data_source.remote.model


import com.squareup.moshi.Json

data class Picture(
    @field:Json(name = "large")
    val large: String,
    @field:Json(name = "medium")
    val medium: String,
    @field:Json(name = "thumbnail")
    val thumbnail: String
)