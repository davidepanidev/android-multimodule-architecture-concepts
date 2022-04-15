package com.davidepani.data.repositories

import com.davidepani.data.api.CoinGeckoApiService
import com.davidepani.data.mappers.DataMapper
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.domain.interfaces.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinGeckoApiService: CoinGeckoApiService,
    private val mapper: DataMapper
) : CoinRepository {

    override suspend fun retrieveMostCapitalizedCoin(): Result<Coin> {
        return try {
            val coinsList = coinGeckoApiService.getCoinsMarkets(
                currency = "usd",
                numCoinsPerPage = 1,
                order = "market_cap_desc"
            )
            val mappedCoin = mapper.mapCoin(coinResponse = coinsList[0])

            Result.Success(mappedCoin)
        } catch(e: Exception) {
            Result.Failure(e)
        }
    }

}