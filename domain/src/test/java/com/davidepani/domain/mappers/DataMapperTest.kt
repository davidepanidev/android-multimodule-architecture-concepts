package com.davidepani.domain.mappers

import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class DataMapperTest {

    private lateinit var cut: DataMapper

    @Before
    fun setUp() {
        cut = DataMapper()
    }

    @Test
    fun test_correct_mapCoin() {
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

        assertEquals(expectedCoin, actualCoin)
    }

    @Test
    fun test_incorrect_mapCoin() {
        val coinName = "Bitcoin"
        val coinResponse = CoinResponse(
            name = coinName
        )
        val unexpectedCoin = Coin(
            name = "Ethereum"
        )

        val actualCoin = cut.mapCoin(
            coinResponse = coinResponse
        )

        assertNotEquals(unexpectedCoin, actualCoin)
    }

}