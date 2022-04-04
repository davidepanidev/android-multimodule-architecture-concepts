package com.davidepani.data.repositories

import com.davidepani.data.api.CoinGeckoApiService
import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinApiResponse
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinGeckoApiService: CoinGeckoApiService
) : CoinRepository {

    override suspend fun retrieveMostCapitalizedCoin(): CoinApiResponse {
        return try {
            val coinsList = coinGeckoApiService.getCoinsMarkets()
            coinsList[0]
        } catch(e: Exception) {
            throw RuntimeException(e.message)
        }
    }

}