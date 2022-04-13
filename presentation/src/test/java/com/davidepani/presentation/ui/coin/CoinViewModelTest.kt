package com.davidepani.presentation.ui.coin

import com.davidepani.androidextensions.tests.BaseViewModelTest
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.domain.usecases.GetMostCapitalizedCoinUseCase
import com.davidepani.presentation.mappers.UiMapper
import com.davidepani.presentation.models.CoinUi
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
class CoinViewModelTest : BaseViewModelTest() {

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

        expectThat(cut.coinLD.value).isEqualTo(expectedCoin)
        expectThat(cut.errorLD.value).isEqualTo(null)
        expectThat(cut.isProgressVisible.value).isEqualTo(false)
        expectThat(cut.isCoinContentVisible.value).isEqualTo(true)
    }

    @Test
    fun `getCoin with Failure result sets expected LiveData error value`() = runTest {
        val expectedException = Exception("Test exception")
        coEvery { getMostCapitalizedCoinUseCase.invoke() } returns Result.Failure(expectedException)

        cut.getCoin()

        expectThat(cut.errorLD.value).isEqualTo(expectedException.message)
        expectThat(cut.coinLD.value).isEqualTo(null)
        expectThat(cut.isCoinContentVisible.value).isEqualTo(false)
        expectThat(cut.isProgressVisible.value).isEqualTo(false)
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

        expectThat(cut.coinLD.value).isEqualTo(expectedCoin)
        expectThat(cut.errorLD.value).isEqualTo(null)
        expectThat(cut.isProgressVisible.value).isEqualTo(false)
        expectThat(cut.isCoinContentVisible.value).isEqualTo(true)
    }

}