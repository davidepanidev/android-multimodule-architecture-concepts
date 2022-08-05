package com.github.davidepanidev.data.mappers

import com.github.davidepanidev.data.BITCOIN_RESOURCE_JSON_FILENAME
import com.github.davidepanidev.data.models.CoinApiResponse
import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.kotlinextensions.deserializeJsonFileFromSystemResources
import com.github.davidepanidev.kotlinextensions.utils.serialization.SerializationManager
import com.github.davidepanidev.kotlinextensions.utils.serialization.gson.GsonSerializationManager
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class DataMapperTest {

    private lateinit var cut: DataMapper

    private val deserializer: SerializationManager = GsonSerializationManager()
    private val bitcoinApiResponse: CoinApiResponse = BITCOIN_RESOURCE_JSON_FILENAME.deserializeJsonFileFromSystemResources(deserializer)

    @Before
    fun setUp() {
        cut = DataMapper()
    }

    @Test
    fun `mapCoin returns expected Coin`() {
        val coinResponse = bitcoinApiResponse
        val expectedCoin = with(coinResponse) {
            Coin(
                name = name,
                price = currentPrice,
                marketCap = marketCap,
                image = image
            )
        }


        val actualCoin = cut.mapCoin(
            coinResponse = coinResponse
        )

        expectThat(actualCoin).isEqualTo(expectedCoin)
    }

}