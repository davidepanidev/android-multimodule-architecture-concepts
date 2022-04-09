package com.davidepani.presentation.mappers

import com.davidepani.domain.entities.Coin
import com.davidepani.presentation.models.CoinUi
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class UiMapper @Inject constructor() {

    fun mapCoinUi(coin: Coin): CoinUi {
        val currency = NumberFormat.getCurrencyInstance(Locale.getDefault())
        currency.maximumFractionDigits = 2
        currency.currency = Currency.getInstance("USD")
        val marketCap = currency.format(coin.marketCap)

        return CoinUi(
            name = coin.name,
            marketCap = marketCap,
            imageUrl = coin.image
        )
    }

}