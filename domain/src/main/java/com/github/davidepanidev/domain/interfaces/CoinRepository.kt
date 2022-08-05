package com.github.davidepanidev.domain.interfaces

import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.domain.entities.Result

interface CoinRepository {

    suspend fun retrieveMostCapitalizedCoin(): Result<Coin>

}