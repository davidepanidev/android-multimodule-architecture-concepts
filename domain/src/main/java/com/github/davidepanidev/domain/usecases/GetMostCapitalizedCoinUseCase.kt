package com.github.davidepanidev.domain.usecases

import com.github.davidepanidev.data.interfaces.CoinRepository
import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.domain.entities.Result
import com.github.davidepanidev.domain.entities.Result.Failure
import com.github.davidepanidev.domain.entities.Result.Success
import com.github.davidepanidev.domain.mappers.DataMapper
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