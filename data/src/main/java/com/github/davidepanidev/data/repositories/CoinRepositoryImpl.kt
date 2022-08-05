package com.github.davidepanidev.data.repositories

import com.github.davidepanidev.data.api.CoinGeckoApiService
import com.github.davidepanidev.data.mappers.DataMapper
import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.domain.entities.Result
import com.github.davidepanidev.domain.interfaces.CoinRepository
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