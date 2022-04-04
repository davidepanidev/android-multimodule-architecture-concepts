package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinApiResponse
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
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
class GetCoinUseCaseTest {

    private lateinit var cut: GetMostCapitalizedCoinUseCase
    @MockK private lateinit var coinRepository: CoinRepository
    @MockK private lateinit var dataMapper: DataMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetMostCapitalizedCoinUseCase(
            coinRepository = coinRepository,
            dataMapper = dataMapper
        )
    }

    @Test
    fun invoke_returnsExpectedCoin() = runTest {
        val coinResponse = CoinApiResponse(name = "Bitcoin")
        val expectedCoin = Coin(name = "Bitcoin")

        coEvery { coinRepository.retrieveMostCapitalizedCoin() } returns coinResponse
        every { dataMapper.mapCoin(coinResponse = coinResponse) } returns expectedCoin
        
        val actualCoin = cut.invoke()

        expectThat(actualCoin).isEqualTo(expectedCoin)
    }

}