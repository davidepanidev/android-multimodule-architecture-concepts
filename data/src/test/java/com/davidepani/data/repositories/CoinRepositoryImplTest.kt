package com.davidepani.data.repositories

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isNotEmpty

@ExperimentalCoroutinesApi
class CoinRepositoryImplTest {

    private lateinit var cut: CoinRepositoryImpl

    @Before
    fun setUp() {
        cut = CoinRepositoryImpl()
    }

    @Test
    fun retrieveCoin_returnCoinWithNotEmptyName() = runTest {
        val actualResponse = cut.retrieveCoin()
        expectThat(actualResponse.name).isNotEmpty()
    }

}