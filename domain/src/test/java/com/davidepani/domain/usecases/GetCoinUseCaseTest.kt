package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class GetCoinUseCaseTest {

    private lateinit var cut: GetCoinUseCase
    private val coinRepository: CoinRepository = mockk()
    private val dataMapper: DataMapper = mockk()


    @Before
    fun setUp() {
        clearAllMocks()

        cut = GetCoinUseCase(
            coinRepository = coinRepository,
            dataMapper = dataMapper
        )
    }

    @Test
    fun invoke_returnsExpectedCoin() = runTest {
        val coinResponse = CoinResponse(name = "Bitcoin")
        val expectedCoin = Coin(name = "Bitcoin")

        coEvery { coinRepository.retrieveCoin() } returns coinResponse
        every { dataMapper.mapCoin(coinResponse = coinResponse) } returns expectedCoin
        
        val actualCoin = cut.invoke()

        expectThat(actualCoin).isEqualTo(expectedCoin)
    }

}