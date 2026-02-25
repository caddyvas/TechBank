package com.deep.techbank.data.remote

import com.deep.techbank.data.remote.dto.UserDto
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

}
