package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class GetCoinUseCaseTest {

    private lateinit var cut: GetCoinUseCase
    @MockK private lateinit var coinRepository: CoinRepository
    @MockK private lateinit var dataMapper: DataMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetCoinUseCase(
            coinRepository = coinRepository,
            dataMapper = dataMapper
        )
    }

    @Test
    fun invoke_returnsExpectedCoin() = runBlockingTest {
        val coinResponse = CoinResponse(name = "Bitcoin")
        val expectedCoin = Coin(name = "Bitcoin")

        coEvery { coinRepository.retrieveCoin() } returns coinResponse
        every { dataMapper.mapCoin(coinResponse = coinResponse) } returns expectedCoin
        
        val actualCoin = cut.invoke()

        expectThat(actualCoin).isEqualTo(expectedCoin)
    }

}