package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo

class GetUser(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(id: Int): UserEntity? {
        return repository.getUserById(id)
    }
}