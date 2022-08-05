package com.github.davidepanidev.data.mappers

import com.github.davidepanidev.data.models.CoinApiResponse
import com.github.davidepanidev.domain.entities.Coin
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