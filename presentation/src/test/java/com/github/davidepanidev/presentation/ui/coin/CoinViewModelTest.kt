package com.github.davidepanidev.presentation.ui.coin

import com.github.davidepanidev.androidextensions.tests.BaseCoroutineTestWithTestDispatcherProviderAndInstantTaskExecutorRule
import com.github.davidepanidev.domain.entities.Coin
import com.github.davidepanidev.domain.entities.Result
import com.github.davidepanidev.domain.usecases.GetMostCapitalizedCoinUseCase
import com.github.davidepanidev.presentation.mappers.UiMapper
import com.github.davidepanidev.presentation.models.CoinUi
import com.github.davidepanidev.presentation.models.CoinUiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class CoinViewModelTest : BaseCoroutineTestWithTestDispatcherProviderAndInstantTaskExecutorRule(
    UnconfinedTestDispatcher()
) {

    private lateinit var cut: CoinViewModel
    @MockK private lateinit var getMostCapitalizedCoinUseCase: GetMostCapitalizedCoinUseCase
    @MockK private lateinit var mapper: UiMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = CoinViewModel(
            getMostCapitalizedCoinUseCase = getMostCapitalizedCoinUseCase,
            mapper = mapper
        )
    }


    @Test
    fun `getCoin with Success result sets expected LiveData Coin value`() = runTest {
        val coin = Coin(
            name = "Bitcoin",
            price = 0.0,
            marketCap = 1200,
            image = ""
        )

        coEvery { getMostCapitalizedCoinUseCase.invoke() } returns Result.Success(coin)

        val expectedCoin = CoinUi(
            name = "Bitcoin",
            marketCap = "1,200.00 $",
            imageUrl = ""
        )

        every { mapper.mapCoinUi(coin = coin) } returns expectedCoin

        cut.getCoin()

        expectThat(cut.uiState.value).isA<CoinUiState.Success>().and {
            get { this.coin }.isEqualTo(expectedCoin)
        }
    }

    @Test
    fun `getCoin with Failure result sets expected LiveData error value`() = runTest {
        val expectedException = Exception("Test exception")
        coEvery { getMostCapitalizedCoinUseCase.invoke() } returns Result.Failure(expectedException)

        cut.getCoin()

        expectThat(cut.uiState.value).isA<CoinUiState.Error>().and {
            get { this.message }.isEqualTo(expectedException.toString())
        }
    }

    @Test
    fun `onRetryButtonClick with Success result sets expected LiveData Coin value`() = runTest {
        val coin = Coin(
            name = "Bitcoin",
            price = 0.0,
            marketCap = 1200,
            image = ""
        )

        coEvery { getMostCapitalizedCoinUseCase.invoke() } returns Result.Success(coin)

        val expectedCoin = CoinUi(
            name = "Bitcoin",
            marketCap = "1,200.00 $",
            imageUrl = ""
        )

        every { mapper.mapCoinUi(coin = coin) } returns expectedCoin

        cut.onRetryButtonClick()

        expectThat(cut.uiState.value).isA<CoinUiState.Success>().and {
            get { this.coin }.isEqualTo(expectedCoin)
        }
    }

}