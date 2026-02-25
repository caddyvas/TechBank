package com.deep.techbank.domain.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): Result<Unit>
}
