package com.deep.techbank.data.repository

import com.deep.techbank.data.remote.UsersApiService
import com.deep.techbank.data.remote.dto.UserDto
import com.deep.techbank.domain.model.Users
import com.deep.techbank.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val apiService: UsersApiService
) : UsersRepository {

    private val _usersList: MutableStateFlow<List<Users>> = MutableStateFlow(emptyList())

    // public immutable flow
    override val usersList: Flow<List<Users>> = _usersList

    override suspend fun retrieveUsers() {
        try {
            val userDtos = apiService.getUsers()
            // Map the DTOs to our domain model
            _usersList.value = userDtos.map { it.toDomainModel() }
        } catch (e: Exception) {
            // In a real app, you would handle this error more gracefully
            // For now, we can just print it or emit an error state
            e.printStackTrace()
        }
    }
}

// This is the CORRECT mapping function that converts the full DTO to our clean domain model
fun UserDto.toDomainModel(): Users {
    return Users(
        id = this.id,
        name = this.name,
        email = this.email,
        avatar = this.avatar,
        createdAt = this.createdAt,
        // Use the Elvis operator to provide a default value if accountNumber is null
        accountNumber = this.accountNumber ?: ""
    )
}
