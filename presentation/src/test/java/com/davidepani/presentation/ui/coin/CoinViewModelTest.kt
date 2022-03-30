package com.davidepani.presentation.ui.coin

import com.davidepani.androidextensions.tests.BaseViewModelTest
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.usecases.GetCoinUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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
    fun getCoin() {
        val expectedCoin = Coin(name = "Bitcoin")
        coEvery { getCoinUseCase.invoke() } returns expectedCoin

        cut.getCoin()

        assertEquals(expectedCoin, cut.coinLD.value)
    }

}