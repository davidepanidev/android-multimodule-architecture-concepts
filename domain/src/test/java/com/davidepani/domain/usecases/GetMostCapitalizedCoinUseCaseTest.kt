package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.ETHEREUM_RESOURCE_JSON_FILENAME
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
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
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class GetMostCapitalizedCoinUseCaseTest {

    private lateinit var cut: GetMostCapitalizedCoinUseCase
    @MockK private lateinit var coinRepository: CoinRepository
    @MockK private lateinit var dataMapper: DataMapper

    private val deserializer: SerializationManager = GsonSerializationManager()
    private val ethereumApiResponse: CoinApiResponse = ETHEREUM_RESOURCE_JSON_FILENAME.deserializeJsonFileFromSystemResources(deserializer)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetMostCapitalizedCoinUseCase(
            coinRepository = coinRepository,
            dataMapper = dataMapper
        )
    }

    @Test
    fun `invoke with success api response returns Success with expected Coin`() = runTest {
        val coinResponse = ethereumApiResponse
        val expectedCoin = with(coinResponse) {
            Coin(
                name = name,
                price = currentPrice,
                marketCap = marketCap,
                image = image
            )
        }
        val expectedResult = com.davidepani.domain.entities.Result.Success(expectedCoin)

        coEvery { coinRepository.retrieveMostCapitalizedCoin() } returns Result.success(coinResponse)
        every { dataMapper.mapCoin(coinResponse = coinResponse) } returns expectedCoin
        
        val actualResult = cut.invoke()

        expectThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `invoke with failure api response returns Failure with expected exception`() = runTest {
        val exceptionResponse = Exception("test exception")
        val expectedResult = com.davidepani.domain.entities.Result.Failure(exceptionResponse)

        coEvery { coinRepository.retrieveMostCapitalizedCoin() } returns Result.failure(exceptionResponse)

        val actualResult = cut.invoke()

        expectThat(actualResult).isEqualTo(expectedResult)
    }

}