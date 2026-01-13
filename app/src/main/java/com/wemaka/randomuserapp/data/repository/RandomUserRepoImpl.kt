package com.wemaka.randomuserapp.data.repository

import com.wemaka.randomuserapp.data.data_source.local.UserDao
import com.wemaka.randomuserapp.data.data_source.remote.RandomUserAPI
import com.wemaka.randomuserapp.data.util.toExternal
import com.wemaka.randomuserapp.data.util.toLocal
import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RandomUserRepoImpl(
    private val remote: RandomUserAPI,
    private val local: UserDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RandomUserRepo {
    override suspend fun createRandomUser(
        results: Int,
        gender: String,
        nat: String
    ): Resource<List<User>> {
        return try {
            Resource.Success(
                withContext(dispatcher) {
                    remote.createRandomUser(
                        results = results,
                        gender = gender,
                        nat = nat
                    ).remoteUsers.toExternal()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An unknown error occurred.")
        }
    }

    override fun getAllUsers(): Flow<List<User>> {
        return local.getAllUsers().map { users ->
            withContext(dispatcher) {
                users.toExternal()
            }
        }
    }

    override suspend fun getUserById(id: Int): User? {
        return local.getUserById(id)?.toExternal()
    }

    override suspend fun upsertUser(user: User) {
        local.upsertUser(user.toLocal())
    }

    override suspend fun deleteUser(user: User) {
        local.deleteUser(user.toLocal())
    }
}