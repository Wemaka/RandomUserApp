package com.wemaka.randomuserapp.domain.repository

import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.model.Users
import com.wemaka.randomuserapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface RandomUserRepo {
    suspend fun getRandomUser(
        results: Int,
        gender: String,
        nat: String
    ): Resource<Users>

    fun getAllUsers(): Flow<List<UserEntity>>

    suspend fun getUserById(id: Int): UserEntity?

    suspend fun insertUser(user: UserEntity)

    suspend fun deleteUser(user: UserEntity)
}