package com.github.davidepanidev.data.interfaces

import com.github.davidepanidev.data.models.CoinApiResponse

interface CoinRepository {

    suspend fun retrieveMostCapitalizedCoin(): Result<CoinApiResponse>

}