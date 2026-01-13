package com.wemaka.randomuserapp.data.data_source.remote.model


import com.squareup.moshi.Json

data class Timezone(
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "offset")
    val offset: String
)