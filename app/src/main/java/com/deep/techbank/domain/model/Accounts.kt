package com.deep.techbank.domain.model

data class Accounts(
    val id: Int,
    val userId: Int,
    val type: String,
    val accountNumber: String,
    val balance: Double,
    val currency: String,
    val createdAt: String
)
