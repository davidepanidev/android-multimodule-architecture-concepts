package com.davidepani.data.repositories

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinApiResponse
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor() : CoinRepository {

    override suspend fun retrieveCoin(): CoinApiResponse {
        return CoinApiResponse(name = "Bitcoin")
    }

}