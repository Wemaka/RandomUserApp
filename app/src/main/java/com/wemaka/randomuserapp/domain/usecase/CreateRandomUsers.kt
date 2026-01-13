package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.data.model.User
import com.wemaka.randomuserapp.data.repository.RandomUserRepo
import com.wemaka.randomuserapp.data.util.Resource

class CreateRandomUsers(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(
        results: Int = 1,
        gender: String = "",
        nat: String = ""
    ): Resource<List<User>> {
        return repository.createRandomUser(results, gender, nat)
    }
}