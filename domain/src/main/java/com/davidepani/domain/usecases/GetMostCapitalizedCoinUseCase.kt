package com.davidepani.domain.usecases

import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.domain.interfaces.CoinRepository
import javax.inject.Inject


class GetMostCapitalizedCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke(): Result<Coin> {
        return coinRepository.retrieveMostCapitalizedCoin()
    }

}