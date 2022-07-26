package com.davidepani.data.repositories

import com.davidepani.data.BITCOIN_RESOURCE_JSON_FILENAME
import com.davidepani.data.ETHEREUM_RESOURCE_JSON_FILENAME
import com.davidepani.data.api.CoinGeckoApiService
import com.davidepani.data.mappers.DataMapper
import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.kotlinextensions.deserializeJsonFileFromSystemResources
import com.davidepani.kotlinextensions.utils.serialization.SerializationManager
import com.davidepani.kotlinextensions.utils.serialization.gson.GsonSerializationManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class CoinRepositoryImplTest {

    private lateinit var cut: CoinRepositoryImpl
    @MockK private lateinit var coinGeckoApiService: CoinGeckoApiService
    @MockK private lateinit var mapper: DataMapper

    private val deserializer: SerializationManager = GsonSerializationManager()
    private val bitcoinApiResponse: CoinApiResponse = BITCOIN_RESOURCE_JSON_FILENAME.deserializeJsonFileFromSystemResources(deserializer)
    private val ethereumApiResponse: CoinApiResponse = ETHEREUM_RESOURCE_JSON_FILENAME.deserializeJsonFileFromSystemResources(deserializer)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = CoinRepositoryImpl(
            coinGeckoApiService = coinGeckoApiService,
            mapper = mapper
        )
    }


    @Test
    fun `retrieveMostCapitalizedCoin with Exception returns Failure with expected exception`() = runTest {
        val expectedException = Exception("test exception")
        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } throws expectedException

        val expectedResult = Result.Failure(expectedException)

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isEqualTo(expectedResult)
    }

    @Test
    fun `retrieveMostCapitalizedCoin with empty list returns Failure`() = runTest {
        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } returns emptyList()

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isA<Result.Failure>()
    }

    @Test
    fun `retrieveMostCapitalizedCoin with one item list returns Success with first item`() = runTest {
        val expectedCoin = Coin(
            name = "Bitcoin",
            price = 43000.0,
            marketCap = 89129829,
            image = ""
        )
        val expectedResult = Result.Success(expectedCoin)

        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } returns listOf(
            bitcoinApiResponse
        )
        every { mapper.mapCoin(coinResponse = bitcoinApiResponse) } returns expectedCoin

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isEqualTo(expectedResult)
    }

    @Test
    fun `retrieveMostCapitalizedCoin with two items list returns Success with first item`() = runTest {
        val expectedCoin = Coin(
            name = "Ethereum",
            price = 3000.0,
            marketCap = 891298,
            image = ""
        )
        val expectedResult = Result.Success(expectedCoin)

        coEvery { coinGeckoApiService.getCoinsMarkets(any(), any(), any()) } returns listOf(
            ethereumApiResponse,
            bitcoinApiResponse
        )
        every { mapper.mapCoin(coinResponse = ethereumApiResponse) } returns expectedCoin

        val actualResponse = cut.retrieveMostCapitalizedCoin()

        expectThat(actualResponse).isEqualTo(expectedResult)
    }

}