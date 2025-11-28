package com.wemaka.randomuserapp.data.repository

import com.wemaka.randomuserapp.data.data_source.RandomUserAPI
import com.wemaka.randomuserapp.data.data_source.UserDao
import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.model.Users
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo
import com.wemaka.randomuserapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class RandomUserRepoImpl(
    private val api: RandomUserAPI,
    private val dao: UserDao
) : RandomUserRepo {
    override suspend fun getRandomUser(
        results: Int,
        gender: String,
        nat: String
    ): Resource<Users> {
        return try {
            Resource.Success(
                api.getRandomUser(
                    results = results,
                    gender = gender,
                    nat = nat
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An unknown error occurred.")
        }
    }

    override fun getAllUsers(): Flow<List<UserEntity>> {
        return dao.getAllUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity? {
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        dao.deleteUser(user)
    }
}