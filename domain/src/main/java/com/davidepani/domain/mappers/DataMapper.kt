package com.davidepani.domain.mappers

import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.entities.Coin
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapCoin(coinResponse: CoinApiResponse): Coin {
        return Coin(
            name = coinResponse.name
        )
    }

}