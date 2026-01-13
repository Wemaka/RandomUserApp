package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.repository.RandomUserRepo

class DeleteUser(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(user: User) {
        repository.deleteUser(user)
    }
}