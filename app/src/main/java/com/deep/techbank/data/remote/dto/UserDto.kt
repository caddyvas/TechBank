package com.deep.techbank.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "avatar") val avatar: String,
    @field:Json(name = "createdAt") val createdAt: String,
    @field:Json(name = "accountNumber") val accountNumber: String?
)
