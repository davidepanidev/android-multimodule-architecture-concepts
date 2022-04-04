package com.davidepani.androidmultimodulearchitectureconcepts.di

import com.davidepani.data.api.COINGECKO_API_BASE_URL
import com.davidepani.data.api.CoinGeckoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataApiModule {

    @Provides
    @Singleton
    fun provideCoinGeckoApiService(): CoinGeckoApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(COINGECKO_API_BASE_URL)
        .build()
        .create(CoinGeckoApiService::class.java)

}