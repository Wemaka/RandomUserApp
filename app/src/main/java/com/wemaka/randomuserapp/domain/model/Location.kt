package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json

data class Location(
    @field:Json(name = "city")
    val city: String,
    @field:Json(name = "coordinates")
    val coordinates: Coordinates,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "postcode")
    val postcode: String,
    @field:Json(name = "state")
    val state: String,
    @field:Json(name = "street")
    val street: Street,
    @field:Json(name = "timezone")
    val timezone: Timezone
)