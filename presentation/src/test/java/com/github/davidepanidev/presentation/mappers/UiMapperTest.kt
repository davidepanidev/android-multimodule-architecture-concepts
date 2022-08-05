package com.github.davidepanidev.presentation.mappers

import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.kotlinextensions.utils.currencyformatter.CurrencyFormatter
import com.github.davidepanidev.presentation.models.CoinUi
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class UiMapperTest {

    private val fakeFormattedAmount = "1,200.00 $"
    private val fakeCurrencyFormatter = object : CurrencyFormatter {
        override fun format(amount: Number): String {
            return fakeFormattedAmount
        }

        override fun format(amount: Number, customCurrencySymbol: String): String {
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