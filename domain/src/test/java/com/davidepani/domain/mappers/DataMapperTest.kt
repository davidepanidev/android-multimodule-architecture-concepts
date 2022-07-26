package com.davidepani.domain.mappers

import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.BITCOIN_RESOURCE_JSON_FILENAME
import com.davidepani.domain.entities.Coin
import com.davidepani.kotlinextensions.deserializeJsonFileFromSystemResources
import com.davidepani.kotlinextensions.utils.serialization.SerializationManager
import com.davidepani.kotlinextensions.utils.serialization.gson.GsonSerializationManager
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