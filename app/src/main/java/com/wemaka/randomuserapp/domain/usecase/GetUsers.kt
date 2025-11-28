package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: RandomUserRepo
) {
    operator fun invoke(): Flow<List<UserEntity>> {
        return repository.getAllUsers()
    }
}