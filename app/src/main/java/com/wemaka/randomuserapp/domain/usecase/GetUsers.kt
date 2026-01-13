package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.repository.RandomUserRepo
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: RandomUserRepo
) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getAllUsers()
    }
}