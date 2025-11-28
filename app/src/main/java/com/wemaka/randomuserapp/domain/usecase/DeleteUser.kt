package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.domain.entity.UserEntity
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo

class DeleteUser(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(user: UserEntity) {
        repository.deleteUser(user)
    }
}