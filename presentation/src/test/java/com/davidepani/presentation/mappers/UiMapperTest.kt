package com.davidepani.presentation.mappers

import com.davidepani.domain.entities.Coin
import com.davidepani.kotlinextensions.utils.currencyformatter.CurrencyFormatter
import com.davidepani.presentation.models.CoinUi
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class UiMapperTest {

    private val fakeFormattedAmount = "1,200.00 $"
    private val fakeCurrencyFormatter = object : CurrencyFormatter {
        override fun format(amount: Number): String {
            return fakeFormattedAmount
        }
    }

    private val cut = UiMapper(
        currencyFormatter = fakeCurrencyFormatter
    )


    @Test
    fun `mapCoinUi returns expected mapped CoinUi`() {
        val coin = Coin(
            name = "Bitcoin",
            price = 12.0,
            marketCap = 1200,
            image = ""
        )
        val expectedCoinUi = CoinUi(
            name = coin.name,
            marketCap = fakeFormattedAmount,
            imageUrl = coin.image
        )

        val actualCoinUi = cut.mapCoinUi(coin = coin)

        expectThat(actualCoinUi).isEqualTo(expectedCoinUi)
    }

}