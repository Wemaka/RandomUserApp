package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Info(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val results: Int,
    @field:Json(name = "seed")
    val seed: String,
    @field:Json(name = "version")
    val version: String
)