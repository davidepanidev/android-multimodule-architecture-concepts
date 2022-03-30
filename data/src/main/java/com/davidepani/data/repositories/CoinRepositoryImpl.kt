package com.davidepani.data.repositories

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinResponse
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor() : CoinRepository {

    override suspend fun retrieveCoin(): CoinResponse {
        TODO("Not yet implemented")
    }

}