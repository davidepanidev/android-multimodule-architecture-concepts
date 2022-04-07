package com.davidepani.data.repositories

import com.davidepani.data.api.CoinGeckoApiService
import com.davidepani.data.bitcoinResourceJsonFilename
import com.davidepani.data.ethereumResourceJsonFilename
import com.davidepani.data.models.CoinApiResponse
import com.google.gson.Gson
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

    private val bitcoinApiResponse: CoinApiResponse = bitcoinResourceJsonFilename.deserializeJsonFromResources()
    private val ethereumApiResponse: CoinApiResponse = ethereumResourceJsonFilename.deserializeJsonFromResources()

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
        coEvery { coinGeckoApiService.getCoinsMarkets() } throws expectedException

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isFailure()
        expectThat(actualResponse.exceptionOrNull()).isEqualTo(expectedException)
    }

    @Test
    fun `retrieveMostCapitalizedCoin with empty list returns Failure`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets() } returns emptyList()

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isFailure()
    }

    @Test
    fun `retrieveMostCapitalizedCoin with one item list returns Success with first item`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets() } returns listOf(
            bitcoinApiResponse
        )

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isSuccess()
        expectThat(actualResponse.getOrNull()).isEqualTo(bitcoinApiResponse)
    }

    @Test
    fun `retrieveMostCapitalizedCoin with two items list returns Success with first item`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets() } returns listOf(
            ethereumApiResponse,
            bitcoinApiResponse
        )

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isSuccess()
        expectThat(actualResponse.getOrNull()).isEqualTo(ethereumApiResponse)
    }

}

inline fun<reified T> String.deserializeJsonFromResources(): T {
    val jsonText = ClassLoader.getSystemResource(this).readText()
    return Gson().fromJson(jsonText, T::class.java)
}