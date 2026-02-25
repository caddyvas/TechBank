package com.deep.techbank.data.repository

import com.deep.techbank.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {

    override suspend fun login(username: String, password: String): Result<Unit> {
        // TODO: Make the actual network call to your mock API
        // For now, we'll simulate a successful login
        return Result.success(Unit)
    }
}
