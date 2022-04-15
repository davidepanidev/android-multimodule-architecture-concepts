package com.davidepani.data.mappers

import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.entities.Coin
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapCoin(coinResponse: CoinApiResponse): Coin {
        return Coin(
            name = coinResponse.name,
            price = coinResponse.currentPrice,
            marketCap = coinResponse.marketCap,
            image = coinResponse.image
        )
    }

}