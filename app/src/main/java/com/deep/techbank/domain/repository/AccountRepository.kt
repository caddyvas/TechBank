package com.deep.techbank.domain.repository

import com.deep.techbank.domain.model.Accounts
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    val accounts: Flow<List<Accounts>>;
}