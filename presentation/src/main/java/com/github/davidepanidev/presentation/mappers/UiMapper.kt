package com.github.davidepanidev.presentation.mappers

import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.kotlinextensions.formatToCurrency
import com.github.davidepanidev.kotlinextensions.utils.currencyformatter.CurrencyFormatter
import com.github.davidepanidev.presentation.models.CoinUi
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