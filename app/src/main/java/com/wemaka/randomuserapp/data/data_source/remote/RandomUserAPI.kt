package com.wemaka.randomuserapp.data.data_source.remote

import com.wemaka.randomuserapp.data.data_source.remote.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserAPI {
    @GET("api/")
    suspend fun createRandomUser(
        @Query("results") results: Int,
        @Query("gender") gender: String,
        @Query("nat") nat: String
    ): UsersResponse
}