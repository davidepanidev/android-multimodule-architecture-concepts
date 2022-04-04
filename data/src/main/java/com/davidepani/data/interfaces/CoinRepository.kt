package com.davidepani.data.interfaces

import com.davidepani.data.models.CoinApiResponse

interface CoinRepository {

    suspend fun retrieveMostCapitalizedCoin(): Result<CoinApiResponse>

}