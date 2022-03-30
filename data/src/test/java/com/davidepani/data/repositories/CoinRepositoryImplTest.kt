package com.davidepani.data.repositories

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinRepositoryImplTest {

    private lateinit var cut: CoinRepositoryImpl

    @Before
    fun setUp() {
        cut = CoinRepositoryImpl()
    }

    @Test
    fun retrieveCoin_notEmpty() = runBlockingTest {
        val actualResponse = cut.retrieveCoin()
        assert(actualResponse.name.isNotEmpty())
    }
}