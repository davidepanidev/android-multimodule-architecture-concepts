package com.davidepani.data.interfaces

import com.davidepani.data.models.CoinResponse

interface CoinRepository {

    suspend fun retrieveCoin(): CoinResponse

}