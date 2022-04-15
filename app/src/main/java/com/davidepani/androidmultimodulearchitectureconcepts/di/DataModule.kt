package com.davidepani.androidmultimodulearchitectureconcepts.di

import com.davidepani.data.repositories.CoinRepositoryImpl
import com.davidepani.domain.interfaces.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository

}