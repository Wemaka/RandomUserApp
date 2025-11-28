package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Dob(
    @field:Json(name = "age")
    val age: Int,
    @field:Json(name = "date")
    val date: String
)