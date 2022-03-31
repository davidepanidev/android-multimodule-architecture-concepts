package com.davidepani.data.repositories

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
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
    fun retrieveCoin_returnCoinWithNotEmptyName() = runBlockingTest {
        val actualResponse = cut.retrieveCoin()
        assertThat("the retrieved coin should not have an empty name", actualResponse.name.isNotEmpty())
    }

}