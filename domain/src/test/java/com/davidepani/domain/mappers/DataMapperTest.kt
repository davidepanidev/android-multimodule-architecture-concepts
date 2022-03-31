package com.davidepani.domain.mappers

import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test

class DataMapperTest {

    private lateinit var cut: DataMapper

    @Before
    fun setUp() {
        cut = DataMapper()
    }

    @Test
    fun mapCoin_returnsCorrectMap() {
        val coinName = "Bitcoin"
        val coinResponse = CoinResponse(
            name = coinName
        )
        val expectedCoin = Coin(
            name = coinName
        )

        val actualCoin = cut.mapCoin(
            coinResponse = coinResponse
        )

        assertThat(actualCoin, `is`(expectedCoin))
    }

}