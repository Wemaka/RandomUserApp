package com.wemaka.randomuserapp.data.data_source

import com.wemaka.randomuserapp.domain.model.Users
import retrofit2.http.Query
import retrofit2.http.GET

interface RandomUserAPI {
    @GET("api/")
    suspend fun getRandomUser(
        @Query("results") results: Int,
        @Query("gender") gender: String,
        @Query("nat") nat: String
    ): Users
}