package com.github.davidepanidev.androidmultimodulearchitectureconcepts.di

import com.github.davidepanidev.data.interfaces.CoinRepository
import com.github.davidepanidev.data.repositories.CoinRepositoryImpl
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