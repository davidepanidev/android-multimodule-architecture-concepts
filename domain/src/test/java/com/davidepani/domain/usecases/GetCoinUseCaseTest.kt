package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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
    fun correctInvoke() = runBlockingTest {
        val coinResponse = CoinResponse(name = "Bitcoin")
        val expectedCoin = Coin(name = "Bitcoin")

        coEvery { coinRepository.retrieveCoin() } returns coinResponse
        every { dataMapper.mapCoin(coinResponse = coinResponse) } returns expectedCoin
        
        val actualCoin = cut.invoke()

        assertEquals(expectedCoin, actualCoin)
    }

}