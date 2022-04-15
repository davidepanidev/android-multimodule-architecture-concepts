package com.davidepani.domain.usecases

import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.domain.interfaces.CoinRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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


    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetMostCapitalizedCoinUseCase(
            coinRepository = coinRepository
        )
    }

    @Test
    fun `invoke with Success response returns Success with expected Coin`() = runTest {
        val expectedCoin = Coin(
                name = "Bitcoin",
                price = 43000.0,
                marketCap = 279281792,
                image = ""
        )

        val expectedResult = Result.Success(expectedCoin)

        coEvery { coinRepository.retrieveMostCapitalizedCoin() } returns expectedResult

        val actualResult = cut.invoke()

        expectThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `invoke with Failure response returns Failure with expected exception`() = runTest {
        val exceptionResponse = Exception("test exception")
        val expectedResult = Result.Failure(exceptionResponse)

        coEvery { coinRepository.retrieveMostCapitalizedCoin() } returns expectedResult

        val actualResult = cut.invoke()

        expectThat(actualResult).isEqualTo(expectedResult)
    }

}