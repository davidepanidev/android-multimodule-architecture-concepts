package com.davidepani.data.repositories

import com.davidepani.data.api.CoinGeckoApiService
import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinApiResponse
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinGeckoApiService: CoinGeckoApiService
) : CoinRepository {

    override suspend fun retrieveMostCapitalizedCoin(): Result<CoinApiResponse> {
        return try {
            val coinsList = coinGeckoApiService.getCoinsMarkets(
                currency = "usd",
                numCoinsPerPage = 1,
                order = "market_cap_desc"
            )
            Result.success(coinsList[0])
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

}