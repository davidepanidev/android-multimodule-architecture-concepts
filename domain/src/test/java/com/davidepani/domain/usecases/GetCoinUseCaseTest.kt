package com.davidepani.domain.usecases

import com.davidepani.data.interfaces.CoinRepository
import com.davidepani.data.models.CoinResponse
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.mappers.DataMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetCoinUseCaseTest {

    private lateinit var cut: GetCoinUseCase
    private lateinit var coinRepository: CoinRepository
    private lateinit var dataMapper: DataMapper

    @Before
    fun setUp() {
        coinRepository = mockk()
        dataMapper = mockk()
        cut = GetCoinUseCase(
            coinRepository = coinRepository,
            dataMapper = dataMapper
        )
    }

    @Test
    fun correctInvoke() {
        val coinResponse = CoinResponse(name = "Bitcoin")
        val expectedCoin = Coin(name = "Bitcoin")

        coEvery { coinRepository.retrieveCoin() } returns coinResponse
        every { dataMapper.mapCoin(coinResponse = coinResponse) } returns expectedCoin
        
        val actualCoin = cut.invoke()

        assertEquals(expectedCoin, actualCoin)
    }

}