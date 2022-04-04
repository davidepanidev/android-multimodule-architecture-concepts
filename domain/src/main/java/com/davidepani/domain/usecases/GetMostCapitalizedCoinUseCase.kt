package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.domain.entities.Result.Failure
import com.davidepani.domain.entities.Result.Success
import com.davidepani.domain.mappers.DataMapper
import javax.inject.Inject


class GetMostCapitalizedCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    private val dataMapper: DataMapper
) {

    suspend operator fun invoke(): Result<Coin> {
        val response = coinRepository.retrieveMostCapitalizedCoin()

        return response.getOrNull()?.let {
            val coin = dataMapper.mapCoin(coinResponse = it)
            Success(coin)
        } ?: Failure(response.exceptionOrNull())
    }

}