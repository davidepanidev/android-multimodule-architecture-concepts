package com.github.davidepanidev.domain.usecases

import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.domain.entities.Result
import com.github.davidepanidev.domain.interfaces.CoinRepository
import javax.inject.Inject


class GetMostCapitalizedCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke(): Result<Coin> {
        return coinRepository.retrieveMostCapitalizedCoin()
    }

}