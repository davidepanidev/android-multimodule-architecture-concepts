package com.github.davidepanidev.data.repositories

import com.github.davidepanidev.data.api.CoinGeckoApiService
import com.github.davidepanidev.data.interfaces.CoinRepository
import com.github.davidepanidev.data.models.CoinApiResponse
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