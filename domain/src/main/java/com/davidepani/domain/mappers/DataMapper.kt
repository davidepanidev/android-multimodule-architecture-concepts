package com.davidepani.domain.mappers

import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapCoin(coinResponse: CoinResponse): Coin {
        return Coin(
            name = coinResponse.name
        )
    }

}