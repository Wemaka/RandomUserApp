package com.wemaka.randomuserapp.domain.usecase

import com.wemaka.randomuserapp.domain.model.Users
import com.wemaka.randomuserapp.domain.repository.RandomUserRepo
import com.wemaka.randomuserapp.domain.util.Resource

class GetRandomUsers(
    private val repository: RandomUserRepo
) {
    suspend operator fun invoke(
        results: Int = 1,
        gender: String = "",
        nat: String = ""
    ): Resource<Users> {
        return repository.getRandomUser(results, gender, nat)
    }
}