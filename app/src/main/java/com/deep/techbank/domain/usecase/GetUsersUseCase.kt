package com.deep.techbank.domain.usecase

import com.deep.techbank.domain.model.Users
import com.deep.techbank.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) {

    // Expose the flow of users directly from the repository
    val users: Flow<List<Users>> = repository.usersList

    // The primary action is to trigger the retrieval
    suspend operator fun invoke() {
        repository.retrieveUsers()
    }
}
