package com.davidepani.domain.mappers

import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.entities.Coin
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class DataMapperTest {

    private lateinit var cut: DataMapper

    @Before
    fun setUp() {
        cut = DataMapper()
    }

    @Test
    fun mapCoin_returnsCorrectMap() {
        val coinName = "Bitcoin"
        val coinResponse = CoinApiResponse(
            name = coinName
        )
        val expectedCoin = Coin(
            name = coinName
        )

        val actualCoin = cut.mapCoin(
            coinResponse = coinResponse
        )

        expectThat(actualCoin).isEqualTo(expectedCoin)
    }

}