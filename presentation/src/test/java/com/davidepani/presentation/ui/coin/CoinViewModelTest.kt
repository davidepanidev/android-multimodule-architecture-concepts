package com.davidepani.presentation.ui.coin

import com.davidepani.androidextensions.tests.BaseViewModelTest
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.usecases.GetCoinUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class CoinViewModelTest : BaseViewModelTest() {

    private lateinit var cut: CoinViewModel
    @MockK private lateinit var getCoinUseCase: GetCoinUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = CoinViewModel(
            getCoinUseCase = getCoinUseCase
        )
    }

    @Test
    fun getCoin_setsExpectedCoinValue() {
        val expectedCoin = Coin(name = "Bitcoin")
        coEvery { getCoinUseCase.invoke() } returns expectedCoin

        cut.getCoin()

        expectThat(cut.coinLD.value).isEqualTo(expectedCoin)
    }

}