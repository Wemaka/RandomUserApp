package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.model.User
import com.wemaka.randomuserapp.domain.model.toEntity
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo

class AddUser(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(user: User) {
        repository.insertUser(user.toEntity())
    }

    suspend operator fun invoke(user: UserEntity) {
        repository.insertUser(user)
    }
}