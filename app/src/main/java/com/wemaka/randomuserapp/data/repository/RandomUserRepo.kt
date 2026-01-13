package com.wemaka.randomuserapp.data.repository

import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface RandomUserRepo {
    suspend fun createRandomUser(
        results: Int,
        gender: String,
        nat: String
    ): Resource<List<User>>

    fun getAllUsers(): Flow<List<User>>

    suspend fun getUserById(id: Int): User?

    suspend fun upsertUser(user: User)

    suspend fun deleteUser(user: User)
}