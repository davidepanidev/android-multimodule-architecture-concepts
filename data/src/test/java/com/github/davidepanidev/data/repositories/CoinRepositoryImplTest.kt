package com.github.davidepanidev.data.repositories

import com.github.davidepanidev.data.BITCOIN_RESOURCE_JSON_FILENAME
import com.github.davidepanidev.data.ETHEREUM_RESOURCE_JSON_FILENAME
import com.github.davidepanidev.data.api.CoinGeckoApiService
import com.github.davidepanidev.data.models.CoinApiResponse
import com.github.davidepanidev.kotlinextensions.deserializeJsonFileFromSystemResources
import com.github.davidepanidev.kotlinextensions.utils.serialization.SerializationManager
import com.github.davidepanidev.kotlinextensions.utils.serialization.gson.GsonSerializationManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isSuccess

@ExperimentalCoroutinesApi
class CoinRepositoryImplTest {

    private lateinit var cut: CoinRepositoryImpl
    @MockK private lateinit var coinGeckoApiService: CoinGeckoApiService

    private val deserializer: SerializationManager = GsonSerializationManager()
    private val bitcoinApiResponse: CoinApiResponse = BITCOIN_RESOURCE_JSON_FILENAME.deserializeJsonFileFromSystemResources(deserializer)
    private val ethereumApiResponse: CoinApiResponse = ETHEREUM_RESOURCE_JSON_FILENAME.deserializeJsonFileFromSystemResources(deserializer)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = CoinRepositoryImpl(
            coinGeckoApiService = coinGeckoApiService
        )
    }


    @Test
    fun `retrieveMostCapitalizedCoin with Exception returns Failure with expected exception`() = runTest {
        val expectedException = Exception("test exception")
        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } throws expectedException

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isFailure()
        expectThat(actualResponse.exceptionOrNull()).isEqualTo(expectedException)
    }

    @Test
    fun `retrieveMostCapitalizedCoin with empty list returns Failure`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } returns emptyList()

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isFailure()
    }

    @Test
    fun `retrieveMostCapitalizedCoin with one item list returns Success with first item`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } returns listOf(
            bitcoinApiResponse
        )

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isSuccess()
        expectThat(actualResponse.getOrNull()).isEqualTo(bitcoinApiResponse)
    }

    @Test
    fun `retrieveMostCapitalizedCoin with two items list returns Success with first item`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } returns listOf(
            ethereumApiResponse,
            bitcoinApiResponse
        )

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isSuccess()
        expectThat(actualResponse.getOrNull()).isEqualTo(ethereumApiResponse)
    }

}
