package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.repository.RandomUserRepo

class GetUser(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(id: Int): User? {
        return repository.getUserById(id)
    }
}