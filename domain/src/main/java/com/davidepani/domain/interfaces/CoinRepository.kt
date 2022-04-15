package com.davidepani.domain.interfaces

import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result

interface CoinRepository {

    suspend fun retrieveMostCapitalizedCoin(): Result<Coin>

}