package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    private val dataMapper: DataMapper
) {

    suspend operator fun invoke(): Coin {
        val coinResponse = coinRepository.retrieveMostCapitalizedCoin()
        return dataMapper.mapCoin(coinResponse)
    }

}