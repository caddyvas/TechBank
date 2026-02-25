package com.deep.techbank.domain.repository

import com.deep.techbank.domain.model.Users
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    val usersList: Flow<List<Users>>

    suspend fun retrieveUsers()
}