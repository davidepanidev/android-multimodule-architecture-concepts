package com.davidepani.presentation.mappers

import com.davidepani.domain.entities.Coin
import com.davidepani.kotlinextensions.formatToCurrency
import com.davidepani.kotlinextensions.utils.currencyformatter.CurrencyFormatter
import com.davidepani.presentation.models.CoinUi
import javax.inject.Inject

class UiMapper @Inject constructor(
    private val currencyFormatter: CurrencyFormatter
) {

    fun mapCoinUi(coin: Coin): CoinUi {
        return CoinUi(
            name = coin.name,
            marketCap = coin.marketCap.formatToCurrency(currencyFormatter = currencyFormatter),
            imageUrl = coin.image
        )
    }

}