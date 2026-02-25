package com.deep.techbank.domain.usecase

import com.deep.techbank.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        // TODO: Implement login logic using the repository
        // For now, we'll simulate a successful login
        return repository.login(username, password)
    }
}
