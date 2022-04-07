package com.davidepani.data.repositories

import com.davidepani.data.api.CoinGeckoApiService
import com.davidepani.data.models.CoinApiResponse
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
            getCoinApiResponse_Bitcoin()
        )

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isSuccess()
        expectThat(actualResponse.getOrNull()).isEqualTo(getCoinApiResponse_Bitcoin())
    }

    @Test
    fun `retrieveMostCapitalizedCoin with two items list returns Success with first item`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets() } returns listOf(
            getCoinApiResponse_Ethereum(),
            getCoinApiResponse_Bitcoin()
        )

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isSuccess()
        expectThat(actualResponse.getOrNull()).isEqualTo(getCoinApiResponse_Ethereum())
    }


    private fun getCoinApiResponse_Bitcoin() = CoinApiResponse(
        name = "Bitcoin",
        ath = 0.0,
        athChangePercentage = 0.0,
        athDate = "",
        atl = 0.0,
        atlChangePercentage = 0.0,
        atlDate = "",
        circulatingSupply = 0.0,
        currentPrice = 0.0,
        fullyDilutedValuation = 0,
        high24h = 0.0,
        id = "",
        image = "",
        lastUpdated = "",
        low24h = 0.0,
        marketCap = 0,
        marketCapChange24h = 0.0,
        marketCapChangePercentage24h = 0.0,
        marketCapRank = 0,
        maxSupply = 0.0,
        priceChange24h = 0.0,
        priceChangePercentage24h = 0.0,
        roi = "",
        symbol = "",
        totalSupply = 0.0,
        totalVolume = 0.0
    )

    private fun getCoinApiResponse_Ethereum() = CoinApiResponse(
        name = "Ethereum",
        ath = 0.0,
        athChangePercentage = 0.0,
        athDate = "",
        atl = 0.0,
        atlChangePercentage = 0.0,
        atlDate = "",
        circulatingSupply = 0.0,
        currentPrice = 0.0,
        fullyDilutedValuation = 0,
        high24h = 0.0,
        id = "",
        image = "",
        lastUpdated = "",
        low24h = 0.0,
        marketCap = 0,
        marketCapChange24h = 0.0,
        marketCapChangePercentage24h = 0.0,
        marketCapRank = 0,
        maxSupply = 0.0,
        priceChange24h = 0.0,
        priceChangePercentage24h = 0.0,
        roi = "",
        symbol = "",
        totalSupply = 0.0,
        totalVolume = 0.0
    )

}