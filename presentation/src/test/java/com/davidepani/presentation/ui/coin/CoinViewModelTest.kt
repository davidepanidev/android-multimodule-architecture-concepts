package com.davidepani.presentation.ui.coin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.usecases.GetCoinUseCase
import com.davidepani.presentation.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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
    fun getCoin() = testCoroutineRule.runBlockingTest {
        val expectedCoin = Coin(name = "Bitcoin")
        coEvery { getCoinUseCase.invoke() } returns expectedCoin

        cut.getCoin()

        assertEquals(expectedCoin, cut.coinLD.value)
    }

}